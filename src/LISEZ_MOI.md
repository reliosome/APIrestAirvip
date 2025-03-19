Instructions pour rajouter des tables à l'API rest


1. Créer une classe NomTable.java
2. Mettez le nom de la table sous ce format en haut de la classe :
   @Table(name = "Aeroport", schema = "dbo")
3. Ajouter les attributs comme pour la classe Aeroport, assurez-vous que les noms correspondent exactement aux noms des colonnes dans la BD
4. Ajouter une interface 'NomTableRepository' comme AeroportRepository, changer la classe dans JpaRepository pour la bonne classe
5. Créer une classe controller 'NomTableController'. Il faut juste définir les méthodes de base pour votre classe et en haut changer l'annotation @RequestMapping("/aeroports") pour un le nom de la classe