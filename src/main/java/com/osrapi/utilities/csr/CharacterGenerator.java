package com.osrapi.utilities.csr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.hateoas.Resource;

import com.osrapi.controllers.csr.CSRBirthAspectController;
import com.osrapi.controllers.csr.CSRFamilyStatusController;
import com.osrapi.controllers.csr.CSRFatherVocationController;
import com.osrapi.controllers.csr.CSRGenderController;
import com.osrapi.controllers.csr.CSRRaceController;
import com.osrapi.controllers.csr.CSRSiblingRankController;
import com.osrapi.controllers.csr.CSRSocialClassController;
import com.osrapi.models.csr.CSRBirthAspectEntity;
import com.osrapi.models.csr.CSRFamilyStatusEntity;
import com.osrapi.models.csr.CSRFatherVocationEntity;
import com.osrapi.models.csr.CSRGenderEntity;
import com.osrapi.models.csr.CSRIoPcDataEntity;
import com.osrapi.models.csr.CSRSiblingRankEntity;
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
    private CSRFatherVocationEntity getLandedKnightVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        do {
	        int roll = Diceroller.getInstance().rolldX(100);
	        System.out.println("rolled "+roll+" for landed knight vocation");
	        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
	        	CSRFatherVocationEntity voc =
	        			fatherVocations.get(i).getContent();
	        	if (voc.getSocialClass().getName().indexOf("LANDED") >= 0
	        			&& voc.getName().indexOf("Knight") >= 0
	        			&& voc.getRollMin() <= roll
	        			&& voc.getRollMax() >= roll) {
	        		entity = voc;
	        		voc = null;
	        		break;
	        	}
        		voc = null;
	        }
        } while (entity == null);
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getBanneretteVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        do {
	        int roll = Diceroller.getInstance().rolldX(100);
	        System.out.println("rolled "+roll+" for bannerette vocation");
	        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
	        	CSRFatherVocationEntity voc =
	        			fatherVocations.get(i).getContent();
	        	if (voc.getSocialClass().getName().indexOf("LANDED") >= 0
	        			&& voc.getName().indexOf("Banner") >= 0
	        			&& voc.getRollMin() <= roll
	        			&& voc.getRollMax() >= roll) {
	        		entity = voc;
	        		voc = null;
	        		break;
	        	}
        		voc = null;
	        }
        } while (entity == null);
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getBaronVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        do {
	        int roll = Diceroller.getInstance().rolldX(100);
	        System.out.println("rolled "+roll+" for baron vocation");
	        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
	        	CSRFatherVocationEntity voc =
	        			fatherVocations.get(i).getContent();
	        	if (voc.getSocialClass().getName().indexOf("BARON") >= 0
	        			&& voc.getRollMin() <= roll
	        			&& voc.getRollMax() >= roll) {
	        		entity = voc;
	        		voc = null;
	        		break;
	        	}
        		voc = null;
	        }
        } while (entity == null);
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getTitledNobleVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        do {
	        int roll = Diceroller.getInstance().rolldX(98);
	        System.out.println("rolled "+roll+" for titled vocation");
	        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
	        	CSRFatherVocationEntity voc =
	        			fatherVocations.get(i).getContent();
	        	if (voc.getSocialClass().getName().indexOf("TITLED") >= 0
	        			&& voc.getRollMin() <= roll
	        			&& voc.getRollMax() >= roll) {
	        		entity = voc;
	        		voc = null;
	        		break;
	        	}
        		voc = null;
	        }
        } while (entity == null);
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getKingVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getByName("King(KG)");
        CSRFatherVocationEntity entity = fatherVocations.get(0).getContent();
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getRoyalVocation() {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        do {
	        int roll = Diceroller.getInstance().rolldXPlusY(50, 50);
	        System.out.println("rolled "+roll+" for royal vocation");
	        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
	        	CSRFatherVocationEntity voc =
	        			fatherVocations.get(i).getContent();
	        	if (voc.getSocialClass().getName().indexOf("TITLED") >= 0
	        			&& voc.getRollMin() <= roll
	        			&& voc.getRollMax() >= roll) {
	        		entity = voc;
	        		voc = null;
	        		break;
	        	}
        		voc = null;
	        }
        } while (entity == null);
        fatherVocations = null;
        return entity;
    }
    private CSRFatherVocationEntity getFatherVocation(final long socId) {
        List<Resource<CSRFatherVocationEntity>> fatherVocations = 
                CSRFatherVocationController.getInstance().getAll();
        CSRFatherVocationEntity entity = null;
        int roll = Diceroller.getInstance().rolldX(100);
        System.out.println("rolled "+roll+" for father vocation");
        for (int i = fatherVocations.size() - 1; i >= 0; i--) {
        	CSRFatherVocationEntity voc =
        			fatherVocations.get(i).getContent();
        	if (voc.getSocialClass().getId() == socId
        			&& voc.getRollMin() <= roll
        			&& voc.getRollMax() >= roll) {
        		entity = voc;
        		voc = null;
        		break;
        	}
    		voc = null;
        }
        fatherVocations = null;
        return entity;
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
		// STEP 5 - PC Backgrounds
        // SOCIAL CLASS
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
        System.out.println("rolled "+roll+" for social class");
        // FATHERS VOCATION
        if (entity.getSocialClass().getName().indexOf("LANDED") >= 0) {
        	entity.setFatherVocation(getLandedKnightVocation());
        } else if (entity.getSocialClass().getName().indexOf("BANNER") >= 0) {
        	entity.setFatherVocation(getBanneretteVocation());
        } else if (entity.getSocialClass().getName().indexOf("TITLED") >= 0) {
        	entity.setFatherVocation(getTitledNobleVocation());
        } else if (entity.getSocialClass().getName().indexOf("ROYALTY") >= 0) {
        	entity.setFatherVocation(getRoyalVocation());
        } else {
        	entity.setFatherVocation(
        			getFatherVocation(entity.getSocialClass().getId()));
        }
        if (entity.getFatherVocation().getSocialClass().getName()
        		.indexOf("LANDLESS") >= 0) {
        	// assign overlord
        	CSRFatherVocationEntity voc = entity.getFatherVocation();
    		if (voc.getName().toLowerCase().indexOf("landed") >= 0) {
    			voc.setOverlord(getLandedKnightVocation());
    		} else if (voc.getName().toLowerCase().indexOf("banner") >= 0) {
    			voc.setOverlord(getBanneretteVocation());
    		} else if (voc.getName().toLowerCase().indexOf("baron") >= 0) {
    			voc.setOverlord(getBaronVocation());
    		} else if (voc.getName().toLowerCase().indexOf("titled") >= 0) {
    			voc.setOverlord(getTitledNobleVocation());
    		} else if (voc.getName().toLowerCase().indexOf("royal") >= 0) {
    			voc.setOverlord(getRoyalVocation());
    		} else if (voc.getName().toLowerCase().indexOf("king") >= 0) {
    			voc.setOverlord(getKingVocation());
    		}
    		voc = null;
        }
        // STEP 6 - Determine Sibling Rank
        roll = Diceroller.getInstance().rolldX(100);
        List<Resource<CSRSiblingRankEntity>> ranks = 
                CSRSiblingRankController.getInstance().getAll();
        for (int i = ranks.size() - 1; i >= 0; i--) {
            if (ranks.get(i).getContent().getRollMin() <= roll
                    && ranks.get(i).getContent().getRollMax() >= roll) {
                entity.setRank(ranks.get(i).getContent());
                break;
            }
        }
        System.out.println("rolled "+roll+" for sibling rank");
        // STEP 6 - Status in One's Family
        roll = Diceroller.getInstance().rolldX(100);
        List<Resource<CSRFamilyStatusEntity>> statuses = 
                CSRFamilyStatusController.getInstance().getAll();
        for (int i = statuses.size() - 1; i >= 0; i--) {
            if (statuses.get(i).getContent().getRollMin() <= roll
                    && statuses.get(i).getContent().getRollMax() >= roll) {
                entity.setStatus(statuses.get(i).getContent());
                break;
            }
        }
        System.out.println("rolled "+roll+" for family status");
        // STEP 4 - Determine Personal Attributes
        int pts = 135;
        pts += entity.getAspect().getPointsAdjustment();
        pts += entity.getSocialClass().getPointsAdjustment();
        pts += entity.getRank().getPointsAdjustment();
        pts += entity.getStatus().getPointsAdjustment();
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
        } while (sum < pts);
        if (entity.getAttributes() == null) {
            entity.setAttributes(new HashMap<String, Integer>());
        }
        while (!rolls.isEmpty()) {
            String attribute = (String)
                    Diceroller.getInstance().getRandomObject(attributes);
            attributes.remove(attribute);
            entity.getAttributes().put(attribute, rolls.remove(0));
        }
        // SET GENDER
        roll = Diceroller.getInstance().rolldXPlusY(2, -1);
        entity.setGender(CSRGenderController.getInstance().getById(
                (long) roll).get(0).getContent());
        // STEP 12 - Determine Character's Height
        int height = Diceroller.getInstance().rollXdY(2, 10);
        int build = Diceroller.getInstance().rolldX(10);
        switch (entity.getGender().getId().intValue()) {
        case 0:
            height += 62;
            build += 2;
            break;
            default:
                height += 59;
                build--;
        }
        if (entity.getAttributes().get("AGIL") >= 15
                && entity.getAttributes().get("AGIL") <= 19) {
            build--;
        } else if (entity.getAttributes().get("AGIL") >= 15
                && entity.getAttributes().get("AGIL") <= 19) {
            build -= 2;
        }
        if (entity.getAttributes().get("CON") >= 15
                && entity.getAttributes().get("CON") <= 19) {
            build++;
        } else if (entity.getAttributes().get("CON") >= 15
                && entity.getAttributes().get("CON") <= 19) {
            build += 2;
        }
        if (build < 0) {
            build = 0;
        }
        int weight = 10 + (height - 40) * 5;
        switch (build) {
        case 0:
            weight -= (int) ((float) weight * .3f);
            break;
        case 1:
            weight -= (int) ((float) weight * .25f);
            break;
        case 2:
            weight -= (int) ((float) weight * .2f);
            break;
        case 3:
            weight -= (int) ((float) weight * .15f);
            break;
        case 4:
            weight -= (int) ((float) weight * .05f);
            break;
        case 5:
            break;
        case 6:
            weight += (int) ((float) weight * .05f);
            break;
        case 7:
            weight += (int) ((float) weight * .1f);
            break;
        case 8:
            weight += (int) ((float) weight * .15f);
            break;
        case 9:
            weight += (int) ((float) weight * .2f);
            break;
        case 10:
            weight += (int) ((float) weight * .25f);
            break;
        case 11:
            weight += (int) ((float) weight * .3f);
            break;
        case 12:
            weight += (int) ((float) weight * .35f);
            break;
        default:
            weight += (int) ((float) weight * .4f);
        }
        entity.setBags(build);
        entity.setHeight(height);
        entity.setWeight(weight);
        int bp = 0;
        if (weight <= 44) {
            bp = 10;
        } else if (weight >= 45
                && weight <= 53) {
            bp = 11;
        } else if (weight >= 54
                && weight <= 64) {
            bp = 12;
        } else if (weight >= 65
                && weight <= 75) {
            bp = 13;
        } else if (weight >= 76
                && weight <= 87) {
            bp = 14;
        } else if (weight >= 88
                && weight <= 100) {
            bp = 15;
        } else if (weight >= 101
                && weight <= 113) {
            bp = 16;
        } else if (weight >= 114
                && weight <= 128) {
            bp = 17;
        } else if (weight >= 129
                && weight <= 144) {
            bp = 18;
        } else if (weight >= 145
                && weight <= 160) {
            bp = 19;
        } else if (weight >= 161
                && weight <= 177) {
            bp = 20;
        } else if (weight >= 178
                && weight <= 196) {
            bp = 21;
        } else if (weight >= 197
                && weight <= 215) {
            bp = 22;
        } else if (weight >= 216
                && weight <= 235) {
            bp = 23;
        } else if (weight >= 236
                && weight <= 256) {
            bp = 24;
        } else if (weight >= 257
                && weight <= 277) {
            bp = 25;
        } else if (weight >= 278
                && weight <= 300) {
            bp = 26;
        } else if (weight >= 301
                && weight <= 324) {
            bp = 27;
        } else if (weight >= 325
                && weight <= 348) {
            bp = 28;
        } else if (weight >= 349
                && weight <= 373) {
            bp = 29;
        } else if (weight >= 374
                && weight <= 400) {
            bp = 30;
        } else if (weight >= 401
                && weight <= 427) {
            bp = 31;
        } else if (weight >= 428
                && weight <= 455) {
            bp = 32;
        } else if (weight >= 456
                && weight <= 484) {
            bp = 33;
        }
        bp += entity.getAttributes().get("CON");
        bp += entity.getAttributes().get("STR") / 2;
        entity.getAttributes().put("BP", bp);
        entity.getAttributes().put("MBP", bp);
		return entity;
	}
}
