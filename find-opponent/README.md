# FindOpponent

- html frontend
 /index.html allows adding ready users
  shows all ready users when 'Add ready player' pressed
  Displays opponent when 'Find Opponent' pressed
  
- api
 + GET /api/ready-players
   lists all ready players
   
 + POST /api/ready-players ```{"name": name, "rating": rating}```
   add a new ready player
   
 + DELETE /api/ready-players
   remove all ready players
 
 + DELETE /api/ready-players?filter=true ```[{"name": name, "rating": rating}]```
   remove specified players
    
 + DELETE /api/read-players/opponent ```{"name": name, "rating": rating}```
   find an opponent and remove them from ready players
   
- domain  
 + Player
  NOTE: players with equal fields are equal
 + OpponentFinderImpl
  NOTE: can contain equal fields however only one instance being removed at a time

# Build

1. gradle :find-opponent:war
2. war file location: /find-opponent/build/libs/
3. push to AS or Tomcat

  