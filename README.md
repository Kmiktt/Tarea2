# Estudiantes:


Camila Alejandra Garcia Torres (Kmiktt)

Benjamin Alejandro Stuckrath Bustamante (Stuckrath)

Fernanda Catalina Rubilar Sánchez (Catalinafer2023)

Los cambios hechos en el UML:

-OrganizarReunión en Empleado para tener una forma de representar el hecho de que es un Empleado el que organiza la reunión de acuerdo con el diagrama de UML original

-Unirse a reunion en interfaz Invitable pues toda clase que pueda ser invitada va a tener tambien que tener una forma de unirse a la reunión, por lo que lo más cómodo es agregarlo aqui también

-Metodo de UnirseAReunión pues en diagrama original no habia forma facil de señalizar cuando un objeto Invitable llegaba a la reunión

-Parametros adicionales en Invitación para que los objetos Invitables que reciban la invitación tengan toda la información necesaria para participar (y para tener mensaje de consola más completo)

-FormaDeUnirse en Reunión (fdUnirse) para generar más facilmente el reporte correspondiente

-Método crearInforme para hacer el documento de texto instruido en el pdf