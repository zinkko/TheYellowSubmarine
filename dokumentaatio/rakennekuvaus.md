Yleinen rakenne
===============

Sovelluksen luokat on jaettu kolmeen pakkaukseen: käyttöliittymään, logiikkaan, ja malleihin. Tämän lisäksi on Sovellusluokka, joka luo sovelluksen tärkeimmät luokat, ja käynnistää ne. Logiikassa on sekä pelin että sovelluksen toimintaa ohjaavia luokkia. Malleissa on pelin entiteettejä kuvaavia luokkia, joista jotkut myös toteuttavat jonkin verran toiminnallisuutta. Päävastuu pelin toiminnasta on kuitenkin logiikan puolella. Käyttöliittymä toimi rajapintana käyttäjän ja ohjelman välillä.

Logiikka
========

Logiikassa on neljä luokkaa. Sovelluslogiikka hoitaa koko sovelluksen toimintaa, esimerkiksi siirtymistä pelin ja valikon välillä. Pelilogiikkaa on vastuussa yksittäisestä pelikerrasta, ja käyttää Fysiikka-luokkaa apuna liikkeen mallintamiseen. Tulostenkeraaja keraa ja tallettaa tilastotietoja pelaajalle.

Käyttöliittymä
==============

Käyttöliittymässä on kuuntelijat sekä pelille että sovellukselle. Pelikuuntelija välittää pelaajan käskyt pelin aikana, ja sovelluskuuntelija hoitaa valikkoa.
Käyttöliittyma-luokka on käyttöliittymän pääluokka, joka tietää logiikka luokat ja luo sekä hallinnoi piirtäjää ja kuuntelijoita.

Mallit
======
Malleissa on kartta, kivi, aarre, sekä sukellusvene. Mallit laskevat itse törmäystarkistukset, mutta törmäyksessä tapahtuva toiminnallisuus on logiikan puolella. Logiikalla on viitteet sukellusveneeseen ja karttaan, aarteiden ja kivien käsittely kulkee kartan kautta.

