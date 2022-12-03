package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter
{
    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false;

    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
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
                   final Vec2 thePos, final Vec2 theVelocity, final Hero theHero)
    {
        super(theCharacterType, false, theHitPoints, theDamage, theMaxSpeed, thePos, theVelocity);
        myCharacterType = theCharacterType;
        myHitPoints = theHitPoints; //necessary?
        myHero = theHero;
    }

    protected void attack(Hero theHero){
        super.attack(theHero,super.getMyBoundingBox());
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
    }

    private void approachHero(final Hero theHero)
    {
         /**	player_room.x= floor(player_position.x / 1280);
          player_room.y= floor(player_position.y / 768);
          for (auto &npc : m_entity_manager.get_entities(e_Tag::Enemy))
          {
          c_Vec2 npc_position= npc->get_component<c_Transform>().position;
          c_Vec2 direction;
          c_Vec2 velocity= c_Vec2(0, 0);

          if (npc->get_component<c_Follow_player>().has)
          {
          c_Vec2 npc_room= c_Vec2(floor(npc_position.x / 1280), floor(npc_position.y / 768));
          bool has_sight= false;

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

          if (has_sight)
          {
          direction= m_player->get_component<c_Transform>().position - npc_position;
          velocity= direction * direction.quick_inverse_magnitude() * npc->get_component<c_Follow_player>().speed;
          npc->get_component<c_Transform>().velocity= velocity;
          }
          else if (npc->get_component<c_Follow_player>().home.get_distance_squared(npc_position) > 25)
          {
          direction= npc->get_component<c_Follow_player>().home - npc_position;
          velocity= direction * direction.quick_inverse_magnitude() * npc->get_component<c_Follow_player>().speed;
          npc->get_component<c_Transform>().velocity= velocity;
          }
          else
          {
          npc->get_component<c_Transform>().velocity= velocity;
          }
**/

          }


    //Collision detection
    /**		for (auto &t : m_entity_manager.get_entities(e_Tag::Tile))
     {
     overlap= Physics::get_overlap(e, t);

     // If the bounding boxes overlap
     if (overlap.x > 0 && overlap.y > 0)
     {
     // If the tile blocks movement
     if (t->get_component<c_Bounding_box>().block_move)
     {
     previous_overlap= Physics::get_previous_overlap(e, t);

     // If the overlap is horizontal
     if (previous_overlap.y > 0)
     {
     // If the player came from the left, push them out to the left
     if (e->get_component<c_Transform>().position.x < t->get_component<c_Transform>().position.x)
     {
     e->get_component<c_Transform>().position.x-= overlap.x;
     }
     // If the player came from the right push them out to the right
     else
     {
     e->get_component<c_Transform>().position.x+= overlap.x;
     }
     }
     // If the overlap is vertical
     if (previous_overlap.x > 0)
     {
     if (e->get_component<c_Transform>().position.y < t->get_component<c_Transform>().position.y)
     {
     e->get_component<c_Transform>().position.y-= overlap.y;
     }
     // If the player came from the right push them out to the right
     else
     {
     e->get_component<c_Transform>().position.y+= overlap.y;
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
}