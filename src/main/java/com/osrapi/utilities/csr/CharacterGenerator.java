package com.osrapi.utilities.csr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.hateoas.Resource;

import com.osrapi.controllers.csr.CSRBirthAspectController;
import com.osrapi.controllers.csr.CSRRaceController;
import com.osrapi.controllers.csr.CSRSocialClassController;
import com.osrapi.models.csr.CSRBirthAspectEntity;
import com.osrapi.models.csr.CSRIoPcDataEntity;
import com.osrapi.models.csr.CSRSocialClassEntity;

public class CharacterGenerator {
	private CharacterGenerator() {
		super();
	}
    /** the static instance of {@link CharacterGenerator}. */
    private static CharacterGenerator instance;
    /**
     * Gets the static instance.
     * @return {@link CharacterGenerator}
     */
    public static CharacterGenerator getInstance() {
        if (instance == null) {
        	instance = new CharacterGenerator();
        }
        return instance;
    }
	public CSRIoPcDataEntity getRandomCharacter() {
		CSRIoPcDataEntity entity = new CSRIoPcDataEntity();
		entity.setId((long) 0);
		// STEP 1 - Decide race
		entity.setRace(CSRRaceController.getInstance().getByName(
				"HUMAN").get(0).getContent());
		// STEP 3 - Determine Birth Omens
		int roll = Diceroller.getInstance().rolldX(100);
		List<Resource<CSRBirthAspectEntity>> aspects = 
				CSRBirthAspectController.getInstance().getAll();
		for (int i = aspects.size() - 1; i >= 0; i--) {
			if (aspects.get(i).getContent().getRollMin() <= roll
					&& aspects.get(i).getContent().getRollMax() >= roll) {
				entity.setAspect(aspects.get(i).getContent());
				break;
			}
		}
		aspects = null;
		// STEP 4 - Determine Personal Attributes
		List<Object> attributes = new ArrayList<Object>();
		attributes.add("AGIL");
		attributes.add("INT");
		attributes.add("BV");
		attributes.add("STR");
		attributes.add("WIS");
		attributes.add("APP");
		attributes.add("CON");
		attributes.add("DISC");
		attributes.add("PTY");
		List<Integer> rolls = new ArrayList<Integer>();
		int sum = 0;
		do {
			sum = 0;
			rolls.clear();
			for (int i = attributes.size() + 2; i >= 0; i--) {
				switch (Diceroller.getInstance().rolldXPlusY(19, 1)) {
				case 1: // Historic Character 2-20 range
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
					rolls.add(Diceroller.getInstance().rolldXPlusY(20, 1));
					break;
				case 16: // Heroic Character 2-22 range
				case 17:
				case 18:
				case 19:
					rolls.add(Diceroller.getInstance().rolldXPlusY(21, 1));
					break;
					default: // Super-heroic character 2-25 range
						rolls.add(Diceroller.getInstance().rolldXPlusY(24, 1));
						
				}
			}
			Collections.sort(rolls);
			while (rolls.size() > attributes.size()) {
				rolls.remove(0);
			}
			for (int i = rolls.size() - 1; i >= 0; i--) {
				sum += rolls.get(i);
			}
		} while (sum < 135);
		if (entity.getAttributes() == null) {
			entity.setAttributes(new HashMap<String, Integer>());
		}
		while (!rolls.isEmpty()) {
			String attribute = (String)
					Diceroller.getInstance().getRandomObject(attributes);
			attributes.remove(attribute);
			entity.getAttributes().put(attribute, rolls.remove(0));
		}
		// STEP 5 - PC Backgrounds
		roll = Diceroller.getInstance().rolldX(100);
		List<Resource<CSRSocialClassEntity>> socialClasses = 
				CSRSocialClassController.getInstance().getAll();
		for (int i = socialClasses.size() - 1; i >= 0; i--) {
			if (socialClasses.get(i).getContent().getRollMin() <= roll
					&& socialClasses.get(i).getContent().getRollMax() >= roll) {
				entity.setSocialClass(socialClasses.get(i).getContent());
				break;
			}
		}
		return entity;
	}
}
