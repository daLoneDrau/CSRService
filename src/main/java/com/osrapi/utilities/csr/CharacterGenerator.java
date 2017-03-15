package com.osrapi.utilities.csr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.hateoas.Resource;

import com.osrapi.controllers.csr.CSRBirthAspectController;
import com.osrapi.controllers.csr.CSRRaceController;
import com.osrapi.models.csr.CSRBirthAspectEntity;
import com.osrapi.models.csr.CSRIoPcDataEntity;

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
		for (int i = attributes.size() + 2; i >= 0; i--) {
			switch (Diceroller.getInstance().rolldX(3)) {
			case 1:
			case 2: // Heroic Character 2-22 range
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
		if (entity.getAttributes() == null) {
			entity.setAttributes(new HashMap<String, Integer>());
		}
		while (!rolls.isEmpty()) {
			String attribute = (String)
					Diceroller.getInstance().getRandomObject(attributes);
			attributes.remove(attribute);
			entity.getAttributes().put(attribute, rolls.remove(0));
		}
		return entity;
	}
}
