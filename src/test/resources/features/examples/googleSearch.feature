# Cada archivo .feature contiene una funcionalidad (feature)
# Los archivos feature usan lenguaje Gherkin - BDD (Behavior Driven Development)
# Se recomienda hacerlos en Inglés para evitar problemas de interpretación de palabras clave

Feature: Google Search
  As a user
  I want to search for a term on Google
  So that I can find relevant information

#Feature: Busqueda en Google
#  Como un usuario
#  Quiero hacer una busqueda de un termino en Google
#  Para poder encontrar infromacion relevante

  # Una feature también puede tener un Background, esto sirve para establecer precondiciones como puede ser un login.
  # De esa manera reutilizar ese contexto previo necesario para cada scenario
  Background:
    Given A user is on the Google search page


  # Una feature debe tener diferentes escenarios (scenarios)
  # Estos escenarios usan la estructra Given-When-Then
  Scenario: Search for "Cucumber with Selenium"
#    Given A user is on the Google search page
    When Enters "Cucumber with Selenium" in the search box
    And Clicks the Search button
    Then The user should see results related to "Cucumber with Selenium"

#  Scenario: Busqueda de "Cucumber con Selenium"
#    Given Un usuario se encuentra en la pagina de busqueda de Google
#    When Ingresa "Cucumber con Selenium" en la barra de busqueda
#      And Hace click en el boton Buscar
#    Then Se deben mostrar los resultados de busqueda relacionados a "Cucumber con Selenium"
