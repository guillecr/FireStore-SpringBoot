# Ejemplo de integración de FireStore en SpringBoot
Para que el ejemplo funcione, hay que definir las credenciales en el archivo <code>key.json</code>. Estas credenciales te las suministra FireBase.

Se realiza una simulación de relación de colecciones en una base de datos noSQL mediante el uso de las ID.
En ellas tenemos 2 entidades, Persona y Mascota, ambas con su ID único. La entidad Persona puede albergar una lista de ID de mascotas. De esta manera conseguimos realizar una relación de muchos a uno (n -> 1) las mascotas con las personas.

## Deployed
https://hito2tad.herokuapp.com/vistas
