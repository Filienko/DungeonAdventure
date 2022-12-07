package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Monster extends DungeonCharacter implements ICollidable
{
    /**
     * Monster's default position.
     */
    private final Vec2 myHomePosition = new Vec2((new Random()).nextInt(1, 20),
            (new Random()).nextInt(1, 12));

    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false;


    //TODO:Add variability to the monster's aggression
    private final static int MY_AGGRESSION_DISTANCE = 10;

    /**
     * The specific Monster type.
     */
    private final String myCharacterType;

    /**
     * The specific Monster type.
     */
    private final Hero myHero;

    /**
     * The Monster's hit points (health).
     */
    private final int myHitPoints;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    /**
     * Monster constructor that calls its parent constructor to initialize the Monster's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, chance of healing, and maximum/minimum hit points it can restore.
     * @param theCharacterType The Monster's type.
     * @param theHitPoints The Monster's hit points.
     * @param theDamage The amount of damage Monster can inflict.
     * @param theMaxSpeed The Monster's maximum speed.
     * @param thePos The Monster's location.
     * @param theVelocity The Monster's velocity.
     * @param theHero the Hero associated with the Monsters
     */
    public Monster(final String theCharacterType, final int theHitPoints, final int theDamage, final int theMaxSpeed,
                  final Vec2 thePos, final Vec2 theVelocity, final Hero theHero, final EntityFactory theEntityFactory)
    {
        super(theCharacterType, MY_HERO_STATUS, theHitPoints, theDamage, theMaxSpeed, thePos, theVelocity, theEntityFactory);
        myCharacterType = theCharacterType;
        myEntityFactory = theEntityFactory;
        myHero = theHero;
        myHitPoints = theHitPoints; //necessary?
        myCurrentFrame = 0;
    }

    protected void attack(final Hero theHero) {
        super.attack();
    }

    @Override
    public void update()
    {
        //Replace new Vec2 with hero's position
        if((getMyPos().computeDistance(myHero.getMyPos())<MY_AGGRESSION_DISTANCE))
        {
            //Interact with the Hero's velocity
            approachHero(myHero);
        }
        super.update();

        //movement();
        myCurrentFrame++;
    }

    private void approachHero(final Hero theHero)
    {
        var heroPosition = theHero.getMyPos();
        float roomX = (float) Math.floor(heroPosition.getMyX() / 1216);
        float roomY = (float) Math.floor(heroPosition.getMyY() / 704);
        var heroRoom = new Vec2(roomX, roomY);
        for (var npc:myEntityFactory.getMonsters())
        {
            Vec2 npcPosition = npc.getMyPos();
            Vec2 npcRoom = new Vec2((float) Math.floor(npcPosition.getMyX() / 1216),
                    (float) Math.floor(npcPosition.getMyX() / 704));
            Vec2 direction;
            Vec2 velocity = new Vec2();
            boolean hasSight = false;
            if(heroRoom.equals(npcRoom))
            {
                hasSight = true;
                for (var e: myEntityFactory.getEntities())
                {
                    var notMonster = !e.getType().contains("Ogre") && !e.getType().contains("Rat")
                            && !e.getType().contains("Knight") && !e.getType().contains("Gremlin");
                    var notHero = !e.getType().contains("Priestess") && !e.getType().contains("Warrior")
                            && !e.getType().contains("Thief");
                    var notPotion = !e.getType().contains("Potion");
                    var notPit = !e.getType().contains("Pit");
                    if(notMonster && notHero && notPit)
                    {
                        Vec2 ePosition = e.getMyPos();
                        Vec2 eRoom = new Vec2((float) Math.floor(ePosition.getMyX()/1216),
                                (float) Math.floor(ePosition.getMyX()/704));
                        if(eRoom.equals(npcRoom))
                        {
                            // If there's an intersection then the npc does not have sight on the player
                            if (Physics.entityIntersect(heroPosition, npcPosition, e))
                            {
                                hasSight = false;
                                break;
                            }
                        }
                    }
                }
            }

            if (hasSight)
            {
                direction= myHero.getMyPos().minus(npcPosition);
                velocity= direction.multiply(direction.quickInverseMagnitude() * npc.getMaxSpeed());
            }
            else if (myHomePosition.getDistanceSquared(npcPosition) > 25)
            {
                direction= myHomePosition.minus(npcPosition);
                velocity= direction.multiply(direction.quickInverseMagnitude() * npc.getMaxSpeed());
            }
            npc.setVelocity(velocity);
        }
    }

        /**
         // If player and npc are in the same room
         if (player_room == npc_room)
         {
         has_sight= true;
         // Check sight line against all entities
         for (auto &e : m_entity_manager.get_entities())
         {
         // If the entity has a bounding box and blocks vision
         if (e != npc && e != m_player && e->get_component<c_Bounding_box>().has && e->get_component<c_Bounding_box>().block_vision)
         {
         c_Vec2 e_position= e->get_component<c_Transform>().position;
         // If the npc and entity are in the same room
         c_Vec2 e_room= c_Vec2(floor(e_position.x / 1280), floor(e_position.y / 768));
         if (npc_room == e_room)
         {
         // If there's an intersection then the npc does not have sight on the player
         if (Physics::entity_intersect(player_position, npc_position, e))
         {
         has_sight= false;
         break;
         }
         }
         }
         }
         }
         **/

    @Override
    public String toString()
    {
        return "Monster {" +
                "myCharacterType = '" + myCharacterType + '\'' +
                ", Hero status = " + MY_HERO_STATUS +
                ", myHitPoints = " + myHitPoints +
                '}';
    }

    /**
     * Analyzes whether the collision occurred between two objects and performs certain associated logic
     */
    @Override
    public void collide()
    {

    }
}