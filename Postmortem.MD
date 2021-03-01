# Postmortem Java Solo Adventure

# 1. Inledning
Jag har programmerat ett solo äventyr med en editor.
Man kan spela solo äventyret med ett program (game) och man kan
ändra solo äventyret databasen med ett annat program (editor).

# 1.1 Syfte/Mål
Syftet och målet med arbetet var att ha två program.
Det ena programmet ska vara en java applikation som
använder kan spela spelet med. Det andra programmet
ska användaren kunna ändra speldatabasen så att
links och stories ändras och att spelaren får andra
alternativ/ser annan text när den spelar med mera.
Användaren ska kunna lägga till nya scener och
ändra befintliga med sql insert och update bland annat.
Allt detta med mera har jag lagt till.

# 1.2 Arbetssätt och genomförande
Jag började med att skriva en projektplan om vad
jag måste ha med i projektet plus vad mer jag tänkt ha med.
Jag skrev även en tidsplan om hur jag tänkte att jag
skulle spendera tiden. Sedan så började jag skriva spelmotorn koden.
Sedan forsatte jag med spelmotorn tills den hade
alla nödvändiga funktioner plus många mer.
Efter det så började jag med speleditorn och
började lägga till sql insert, update, delete med mera.
Det står mer exakt vad jag gjort på lektion i min loggbok som jag skrev
efter varje gång jag skrivit kod och den står i ReadMe.

Spelprogrammet går till så här bland annat:
Användaren börjar med att se sagotexten från
databas med story id 1 samt tillhörande länkar
för den sagan/scenen. Användaren kan välja
vilken länk den vill ta (det finns beskrivning för varje
länk). Beroende på vilken länk användaren väljer
så kommer användaren oftast till en annan sen eftersom
de flesta länkar har olika target ids. Exempelvis
så om användaren väljer en länk som leder till scen 3 så
visas en bild på en lampa samt en text i en JTextArea som säger
åt användaren att klicka på lampan. Användaren
måste klicka på lampan för att forsätta till nästa
scen och gör användaren det så kommer spelet fortsätta
och användaren kan se sagotext och välja länk igen men
det kommer även upp en varg bild på en annan position.
Om användaren istället väljer scen 2 så kommer användaren
behöva flytta den röda rutan till den svarta rutan för att forsätta spelet.
Sagotexten visas i en JTextArea och länkarna
finns som JMenuItems. Bilderna visas som ImageIcon i en JLabel, men
formerna ritas ut genom ett Graphics objekt på en custom JPanel i JFramen.
Jag har också lagt till en egen JPanel och JMenuBar
(alltså extendat de klasserna) för att ändra lite färger samt rita ut saker.
<br>
<br>
Editorprogrammet går till såhär:
Användaren kan välja vilken scen/saga den ska använda/se
via en JComboBox. Beroende på vad den väljer så
uppdateras informationen tillhörande den scenen
i en JTextArea. Användaren kan göra saker via
JMenuItems. Användaren kan göra detta:
Edit Story Body, Edit Story ID, Add New Story,
Delete Story, Refresh Stories And Links, Edit Link,
Add New Link och Delete Link. Detta är insert, update
och delete sql kommandon. Det jag inte hann lägga
till var att ta bort alla links/stories, men det
har jag visat att jag kan i loggbok projektet.
Jag har använt en mysql databashanterare som jag skrivit
på loggbok projektet som jag även använder här för att
slippa skriva om all den koden.

<br>
Allt i detta två olika program kan användaren se och använda grafiskt i en Swing JFrame.
Jag har skrivit det grafiska själv istället för att använda den inbyggda IntelliJ editorn eftersom
jag är van med att skriva det grafiska själv.
Jag har använt MVC i hela projektet. All kod är uppdelad utifrån MVC så att
en controller tar hand om model och viewn. Model gör det logiska (databas etc),
view är det grafiska. Jag har använt en mysql databas via en Java Mysql connector API och
använt sql insert, update, delete. Jag har använt
objektsorienterad programmering eftersom jag 
använder objekt i Java. 
<br>
Jag har också gjort mitt soloäventyr samt editorn
avancerare än vad som behövdes till exempel genom
att lägga till: Grafik (bilder, färger, linjer, grafik, former och
använt Swing JFrame samt komponenter med mera),
Tillåt att användaren påverkar spelet (klicka på lampa för att gå vidare, flytta en röd form till en svart form för
att fortsätta spela) och när användaren gör något sådant
så kan en annan bild komma upp eller liknande samt
att användaren kommer till olika scener beroende
på vad användaren väljer för länk/alternativ.
<br>
Jag har också skrivit JavaDocs kommentarer till
i princip all kod (förutom vissa getters och setters
och vissa metoder som jag inte hunnit kommentera).
JavaDocs länken finns i ReadMe. Jag har också
skrivit i min loggbok vad jag gjort och ska göra
efter varje lektion.

# 1.3 Bakgrund
Jag har använt min dator med IntelliJ som är ett programmeringsverktyg där man skriver sin programmeringskod.
Jag har använt mysql connector jar fil som är ett Java API för mysql. Denna fil gör databashantering möjlig från Java.

# 1.4 Positiva erfarenheter
Jag tycker att allt har gått bra och att jag fått med det mesta som jag hade tänkt få med.
Jag har fått med allt som var krav, plus mer.
Dock så skulle jag kunna fått med så att användaren
kan spela och få ett highscore eller liknande 
som sparas i en databas och isåfall då använda login och registreringssystemet
som jag gjorde från loggbok projektet. Jag hann dock inte göra detta, men
men om jag hade hunnit så hade jag gjort det.
Jag hade också kunnat lagt till så att man kan ta bort
alla stories/links på en gång men det hann jag inte heller, dock
så gjorde jag det på loggbok projektet innan.
Jag hade också kunnat lagt till mer funktioner på spelet
som till exempel att användaren kan slåss på någon scen och ett combat system
eller liknande men jag hann inte.
Jag hade också kunnat lagt till så att användaren
kan ändra bilderna som visas i spelet och i vilken
scen som de visas via editorn. Det hann jag inte heller
och om jag skulle göra det så skulle jag behöva
lägga till bilderna i databasen först kanske som base64 eller liknande.
Jag tycker jag har gjort de två programmen effektiva
och laggfria genom att skapa ny tråd när programmet gör mycket samtidigt eller gör
stora operationer i ui tråden.

# 1.5 
Nej inte vad jag kommer på.

# 1.6 
Jag tycker allt gått bra med arbetet. Jag hann det mesta
med arbetet, men det finns nya funktioner man kan lägga till som
det gör i alla program. Jag skrev några förbättringsmöjligheter under positiva erfarenheter.
I framtiden gör jag gärna något större projekt enskilt.

