# Java-oblig-5

Alle oppgavene skal være forsøkt løst etter beste evne (du må ikke løse bonusoppgavene). Hvis du har en oppgave som du har forsøkt på, men ikke fått helt til, er det OK om denne kommenteres ut slik at vi kan se hvordan du har tenkt/hva du har forsøkt på. Resterende kode bør kunne kompileres og kjøres uten at det kræsjer. Oppgaver som er gjennomført og fungerer burde ikke kommenteres ut.

Les: Obligatoriske oppgaver - Informasjon (oppdatert 12.02)

Obligen er strukturert opp i oppgaver, dere skal levere hele IntelliJ prosjektet.

Teorispørsmålene: Ikke .zip denne sammen med programmeringen, men levér som en egen fil. Leveres som docx/pdf/txt navngitt: Oblig5_SUPERNOVA_<DittNavn>.<filtype>
Eks: Oblig5_SUPERNOVA_LarsEmilKnudsen.pdf

Programeringsoppgavene: Leveres som en .zip fil av javaprosjektet navngitt: Oblig5_SUPERNOVA_<DittNavn>.zip 
Eks: Oblig5_SUPERNOVA_LarsEmilKnudsen.zip

Edit:
Lagt til mer forklaring ifbm. skriving til JSON, og legge til @JsonIgnore.
Teori 

Oppgave 1 - Ord og begreper

Lag deg en oversikt over hva følgende ord/begreper/teknologier betyr/er:

    Exception
    Tråd
    Collections Framework
        List
        HashMap
        Queue
        Stack

 Oppgave 2 - Kodesammenligning

Sammenlign med løsningsforslaget fra oblig 4, Oblig4_ProposedSolution.zip. Ta for dere forrige oblig og forklar deres implementasjon. 

Hva har dere gjort? Hvorfor har dere gjort det slik? Hva er forskjellig? Skriv et lite avsnitt om refleksjoner og funn.

Hvis dere har lyst til å gjennomføre en slik sammenligning digitalt med en annen person, fremfor løsningsforslaget, vil dette også være OK.
Programmering

Vi skal fortsette med å utvide oppgaven vi lagde i Oblig 4. Du kan fortsette på din egen implementasjon, eller du kan starte fra løsningsforslaget som finnes her:

Oblig4_ProposedSolution.zip

Video: Oblig 4 - Løsningsforslag

Vi skal ta for oss filskriving, og hente data herfra, samt kunne gjøre både Create, Update og Delete på dataene våre. Slik at vi har hele CRUD-løpet (Create, Read, Update og Delete).

Oppdaterte vue-filer (merk, layout.html er ikke med her, og MÅ være med i prosjektet deres)

Oppgave 2.1 - Fillesing og nytt JSON repository

Vi skal nå kunne gjøre mer enn å bare lese data, vi ønsker derfor å lage og utvide repositoriet vårt.

a - Repository)

Lag et nytt repository kalt UniverseJSONRepository. I konstruktøren skal du kunne ta inn enten et filnavn, eller et File-objekt, og gjør noe lignende slik vi gjorde tidligere, med å lese disse dataene inn i en liste med PlanetSystems. Bruk Jackson eller et lignende bibliotek for å hjelpe til med lesingen av data.

Lag også en egen metode som kan lese inn alle dataene fra filen på nytt.

planets_100.json - Inneholder 100 planeter

planets_4000.json - Inneholder 4000 planeter

b - Forberedelser)

Når JSON serialiserer og deserialiserer (gjør om fra Java-objekter til JSON og tilbake), så gjør den dette ved hjelp av noe som heter reflection (ser hvilke metoder som finnes, og benytter disse). I tilfellet hvor vi ønsker å lese en Planet, så inneholder denne en "CelestialBody". På grunn av arv, så kan denne for oss være en subklasse. Hvis dette er tilfelle må vi gi noe informasjon om at hvis dette er en stjerne, bruk Star-klassen.

Med Jackson, kan man legge til annotering i CelestialBody. Legg til:

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star")
})
public class CelestialBody implements Comparable<CelestialBody> 

I tillegg forutsetter også denne deserialiseringen at vi har tomme konstruktører i klassene vi skal benytte. Da denne benytter disse, for så å fylle opp objektet med data ved hjelp av set-metodene.

Lag tomme konstruktører i alle model-klassene, verifiser også at det finnes get- og set-metoder for alle variabler som skal kunne leses og skrives.

c - Implementasjon av interface)

Implementere IUniverseRepository, og lag en implementasjon av alle metodene derfra.

Benytt så dette repositoriet fremfor det gamle i Application.java. Hvorfor fungerer nå dette sammen med Controllerne våre, selv om vi har et helt nytt repository?

d - Skriving)

Vi ønsker at endringer vi gjør i dataene i senere oppgaver blir lagret, og er der når vi starter applikasjonen på nytt. Vi skal derfor gjøre klart skriving av disse endringene til fil.

Lag en metode som skriver all dataen til fil i JSON-format. Siden det er unødvendig komplisert og oppdatere enkelte områder i en fil med endringene som er gjort er det OK å skrive hele filen på nytt.

For at dette skal gå så smidig som mulig, og du ikke skal ende opp med å skrive data til JSON-format du får problemer med å lese igjen er det noen steg du legge også legge til en annotering.

Legg til @JsonIgnore for alle metoder som returnerer noe, men som du ikke ønsker skal være en del av dataen som skrives til JSON-formatet. Det vil si alt som ikke er get- og -set metoder for instansvariabler.

Ett slikt eksempel er getSurfaceGravity:

@JsonIgnore
public double getSurfaceGravity()

 

Oppgave 2.2 - Fillesing og nytt CSV repository

a - Repository)

Lag enda et nytt repository kalt UniverseCSVRepository. I konstruktøren skal du kunne ta inn enten et filnavn, eller et File-objekt. 

I dette tilfellet ønsker vi å benytte HashMap som den interne lagringsstrukturen fremfor en ArrayList. Hvilke fordeler gir dette oss når vi skal implementere metodene fra IUniverseRepository?

Å lese en .CSV fil krever litt mer kodelogikk. Sørg for at et PlanetSystem og Star bare opprettes én gang, selv om data for dette er duplisert per planet.

planets_100.csv Forhåndsvis dokumentet- Inneholder 100 planeter

planets_4000.csvForhåndsvis dokumentet - Inneholder 4000 planeter

planets_100_with_column_descriptions.csv Forhåndsvis dokumentet- Inneholder 100 planeter, og kolonnebeskrivelser i starten av filen 

planets_4000_with_column_descriptions.csvForhåndsvis dokumentet - Inneholder 4000 planeter, og kolonnebeskrivelser i starten av filen 

b - Interface)

Implementere IUniverseRepository, og lag en implementasjon av alle metodene derfra.

Test så ut ved å bruke denne repositoriet i Application.java.

c - Skriving)

Lag en metode som skriver all dataen til fil i CSV-format. Siden det er unødvendig komplisert og oppdatere enkelte områder i en fil er det OK å skrive hele filen på nytt.

Oppgave 2.3 - Delete

a - Interface)

Du skal utvide interfacet vi laget med følgende metoder:

    Opprette en planet
    Oppdatere en planet
    Slette en planet

Tenk gjennom hvilke parametere du trenger som input for å kunne utføre de forskjellige handlingene. Du trenger ikke lage en implementasjon av disse metodene i repositoryene enda, men de må fortsatt overrides der slik at koden kompilerer (i.e. lage tomme metoder uten en ferdig implementasjon).

b - Delete-metoden)

Velg enten UniverseJSONRepository eller UniverseCSVRespository og implementer så denne metoden i dette repositoriet. Den skal slette en planet, og skrive data til fil (hvis du gjør dette til JSON, ta en titt til på oppgave 2.1d ifbm. @JsonIgnore).

c - Delete - API - Controller)

Oppdater controlleren til å ta imot en API-get request til:

    /api/planet-systems/:planet-system-id/planets/:planet-id/delete

Kall så den korrekte metoden i UniverseCSV/JSONRepository.

Oppgave 2.4 - Create

a - Create-metoden)

Velg enten UniverseJSONRepository eller UniverseCSVRespository og implementer så denne metoden i dette repositoriet. Den skal opprette en planet, og skrive data til fil.

b - Koble opp views)

Kobl sammen planet-create vue med path'en:

    /planet-systems/:planet-system-id/createplanet

c - Create - API - Controller)

Oppdater controlleren til å ta imot en API-post request til:

    /api/planet-systems/:planet-system-id/createplanet

Hent ut data fra post-requesten, dette kan gjøres ved hjelp av formParam-metoden til Context. Pass på å konverter verdiene du får (alt er String-er) til korrekte typer i.e. double. Kall så den korrekte metoden i repositoriet.

Du skal til slutt redirectes tilbake til detaljvisningen for planetsystemet planeten er blitt lagt til i.

Oppgave 2.5 - Update

a - Update-metoden)

Implementer denne metoden i dette valgfritt repository. Den skal oppdatere en planet. Tenk gjennom hvordan du kan få oppdatert det faktiske planet-objektet som finnes i et PlanetSystem. Du står fritt til å utvide med metoder i modell-klassene hvis det skulle være behov for det.

Endringene skal til slutt skrives til fil. Siden det er unødig komplisert og oppdatere spesifikke områder i en fil, så går det fint at hele innholdet i filen overskrives.

b - Update - API - Controller)

Oppdater controlleren til å ta imot en API-post request til:

    /api/planet-systems/:planet-system-id/planets/:planet-id/update

Er det noe kode her som blir felles med create-metoden i controlleren? Kan noe av dette skilles ut i en egen metode begge benytter? Kall den korrekte metoden i det valgte repositoriet.

Du skal til slutt redirectes tilbake til detaljvisningen for planetsystemet planeten er blitt lagt til i.

Oppgave 2.6 - Tråder

Lag en egen tråd for skrivingen til fil i UniverseCSVRepository/UniverseJSONRepository.

 

Bonusoppgaver

Oppgave 3.1 - Database

Opprett en database med en fornuftig tabellstruktur. Gjør nødvendige endringer i .CSV-filene, (splitt opp til å matche tabellene), og importer dataene inn i databasen. 
Lag et nytt database-repository - UniverseDBRepository. Implementer alle metodene fra IUniverseRepository og benytt denne i Application.java.

Hvis databasen er ekstern, sørg for å gjør den tilgjengelig slik at studentassistentene får testet den. Hvis den er lokal, legg med en kopi av databasen i innleveringen.

....
