# Java soloadventure

JavaDocs länk - https://pielun-nti.github.io/Javasoloadventure/

Loggbok, tidsplan och projektplan finns här nere i Readme.

# Projektplan
Projektet ska innehålla ett soloäventyr med ett tilläggsprogram
som man kan ändra soloäventyrets scener med. Jag tänker göra
ett grafiskt gränssnitt till både editorn och spelet. Jag
tänker att man kan genom det grafiska gränsnittet, exempelvis JMenuItems spela 
spelet samt ändra scenerna i spelet. Jag tänker ha en textruta
med text i gränssnittet samt en JMenuBar med JMenuItems och jag tänker
använda Swing JFrame för gränssnittet. Soloäventyret ska vara
så att användaren kan välja olika alternativ och på så sätt
ändra vilken scen användaren tas till. Jag tänker använda
databashanterar programmet som jag gjorde på loggbok projektet
även på detta projekt så att jag slipper skriva om den koden.
Jag kanske även använder samma login & registreringssystem
från loggboken istället för att skriva om den koden.
Om jag hinner så lägger jag till fler funktioner exempelvis kanske så att man kan
få score för hur långt man kommer och att detta sparas i
en databas för denna användaren och att när man loggar in
så kan man se ens score.
# Tidsplan
Jag har 4 veckor (8 lektioner).
<br>
Lektion 1: Planering samt Speläventyrsmotorn
<br>
Lektion 2: Speläventyrsmotorn och eventuellt början på tilläggsprogrammet
<br>
Lektion 3: Tilläggsprogrammet (editorn)
<br>
Lektion 4: Utveckla och lägg till fler funktioner i spelet och editorn
<br>
Lektion 5: Utveckla och lägg till fler funktioner i spelet och editorn
<br>
Lektion 6: Utveckla och lägg till fler funktioner i spelet och editorn
<br>
Lektion 7: Utveckla och lägg till fler funktioner i spelet och editorn
<br>
Lektion 8: Utveckla och lägg till fler funktioner i spelet och editorn

# Loggbok
25-01-2021: Idag har jag planerat vad spelet ska innehålla
och skrivit tidsplan, och sen har jag börjat skriva kod. 
Nästa lektion kommerjag skriva mer kod, 
mer specifikt så ska jag fortsätta på speläventyrsmotorn
eller editorn.

01-02-2021: Idag har jag fortsatt koda spelmotorn, jag har lagt till så att användaren kan välja alternativ från databasen 
genom jmenuitems och klicka sig vidare. Beroende på vad spelaren väljer
så flyttas currentroom till target id och sen visas story för den idn mm.
Nästa gång så ska jag färdigställa detta samt fortsätta så att hela storyn har
fungerade alternativ och story. Sen ska jag också fixa så att svenska Ä och Å 
visas i texten utan att bli konstiga, ska testa med UTF8.

2021-02-08: Fortsatt en del på spelmotorn så att alla alternativ är fungerande. Nästa gång fortsätter jag med att lägga till bilder
eller annat.

2021-02-15: Texten visas med UTF8 nu iallafall. Lagt till bilder i scener i spelet samt färger samt ändrat så en anslutning till databasen
endast skapas i början då programmet körs så att inte onödiga nya anslutningar sker. Jag har också gjort så att
om en bild inte finns så blir scrollviewn med jtextarean hela fönsterstorleken, positionerat element i jframen etc. Nästa gång så tänker jag 
lägga till så att man kan göra mer i spelet, exempelvis klicka på något och då ritas det ut något på skärmen kanske.
Jag tänker också fortsätta med editorn till spelet där man kan ändra scener med insert, update etc. Jag ska också lägga till lite fler nya "new lines"
med \r\n i mysql databas dumpen så att det ser bättre ut och blir lite lättare att läsa i textrutan.

2021-02-16: Jag har gjort så att man måste flytta på en röd ruta till en svart ruta för att fortsätta spela på rum/scene
nr 2. Jag har gjort ritningen genom en custom JPanel. Jag har också lagt till några fler new lines i mysql db dumpen med mera.
Nästa gång så ska jag nästa gång fortsätta på speländraren och kanske lägga till något mer i spelet.
