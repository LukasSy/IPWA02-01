# Fallstudie IPWA02-01

### 1. Derby Apache konfigurieren

### 2. Tomcat 10.1.28 als Application Server verwenden

## Einleitung
MVC Project with Java EE und JSF und Hibernate und Apache DerbyDB

## Technologien
Java Server Faces (JSF)
Apache DerbyDB-Server
TomcatEE 10.01.28 Application Server

Spring Java-Framework
- Entwicklung mit JakarteEE/ JavaEE für Web-Apps vereinfachen

CDI/Beans
- Auf Beans kann man Zugreifen falls sie Insanziert und Initalisiert sind.
- Deklaration mit Unified Expression Language (UEL) @Named, @Inject, ...

Komponentenbibliothek PrimeFaces
- Ein UI Framework für XML Objects wie CheckBox, usw.

Jakarta Persistence API z.B. Hibernate
- Schnittstelle für Java-Anwendungen, die die Zuordnung und Übertragung von Objekten zu Datenbankeinträgen vereinfacht




## Weiteres:
1.) Was bedeutet Persistenz? Laufzeit Objekte in einer Java-Anwendung über eine einzelne Sitzung hinaus zu speichern.

2.) Java EE benutzt **CDI**-Frameworks ("Contexts and Dependency Injection) mit dem Konzept "Beans"

3.) Was sind **Beans**? Beans sind Containerklassen des Datenmodells

4.) Was ist ein **DAO**? DataAcessObject separiert Datenbankzugriff und Business Logik

5.) In JavaServer Faces (JSF) wird das Model durch den FacesServlet bereitgestellt, eine eigenständige Implementierung ist nicht nötig. Der FacesServlet organisiert die korrekte Zuordnung und Darstellung der Daten, die aus der Bean stammen, auf der View, welche durch die .xhtml definiert ist. Diese Trennung der Verantwortlichkeiten gewährleistet eine saubere und strukturierte Datenbindung zwischen Backend und Frontend.

