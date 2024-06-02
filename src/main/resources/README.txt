Como usar el Junit Suite Engine 5 y el Cucumber Suite Engine 7 para poder ejecutar suites:
https://adictosaltrabajo.com/2022/09/06/tests-de-aceptacion-con-cucumber-y-junit-5/

Tener cuidado con el tema del Driver Factory, en algunos lugares se sugiere que se haga una clase con un constructor privado,
    se llame al método creador desde la clase de prueba y se gurade el web driver en una varibale estática.
    Esto funciona cuando estamos haciendo tests aislados y no parametrizados usando DDT, pero el problema es que cuando estamos manejando paralelismos
    es importante aislar el contexto de cada prueba, es decir, que el web driver sea una instancia diferente en cada ejecución (en este caso un escenario).
    Entonces, usando el paradigma de objetos, creando instancias del web driver por cada test estamos usando siempre una instancia de navegador limpia
    (hacemos create al inciio y quit al final e cada uno usando los hooks) en ejecuciones paralelas o en serie que usan DDT como lo hacemos con
    Scenario outline (Cucumber) o alguna de las funcionalidades de Junit5 como puede ser la anotación ParametrizedTest.
    Mismo criterio para cuando usamos objetos Page, la idea es usar POM para encapsular todos los elementos de cada interfaz y su lógica, entonces cada clase
    de estas debe devolver algo o un objeto de su mismo tipo. Por lo tanto, las acciones de tipo void (procedimientos) debería ir encapsuladas en un Test base
    y ser heredado o buscarle la vuelta para usar funciones.