Flappy_fish
===========
 Flappy Fish je naizgled jednostavna igra, kod koje je osnovni zadatak da  se riba  kreće  između prepreka. 
 Svaki prolazak između jednog para prepreka se vrijednuje sa jedan bod. Poslije izvjesnog broja bodova, razmak između 
 prepreka se smanjuje, što povećava težinu igranja.  Ako riba udari u prepreke, igra se zaustavlja.
 
Igra sadrži klase: Flappy_fish, Board, Frame, GameObjects, Fish i  Obstacle. 

Klasa Flappy_fish sadrži metodu main. Klasa koja sadrži metodu main je start programa.

Klasa Board nasljeđuje klasu JPanel.  JPanel definiše nešto poput fizičkog panoa, koji koristimo kao kontejner 
za grupisanje skupa komponenata. Klasa sadrži atribute kao što je širina, dužina boarda , atribut runner klase Thread (nit),
boolean inGame, pause i dr. Podrazumjevani  konstruktor postavlja veličinu table, boju pozadine , font, inicijalizuje
objekte u igri, te pokreće radnu nit, runner.start().
Metoda paint služi za iscrtavanje,  iscrtava razlicite objekte u zavisnosti da li je igra aktivna ili ne. Ukoliko je igra 
aktivna, tj.  inGame = true, iscrtava se teran, pozadina, objekti u igri, u suprotnom  iscrtava se početna slika.
Metoda  update  poziva metode move za kretanje ribe i  move za kretanje prepreka,  zatim povećava skor za jedan ukoliko riba
prođe između prepreka  te smanjuje razmak između prepreka nakon dovoljnog broja bodova.
U klasi Board imamo i klasu GameKeyAdapter u kojoj se upravljačkim tasterima na tataturi (SPACE) govori kako da se riba kreće.

Klasa Fish sadrži atribute kao što su visina i širina ribe, njenu brzinu kretanje, te konstantu koja predstavlja gravitaciju.
Metoda  draw  služi za crtanje animacije. Kada se riba kreće  i njen rep se kreće. To se postiže naizmjeničnim iscrtavanjem
dvije slike. 
Klasu Obstacle sadrži atribute kao što su  koordinate prepreka( gornje i donje). Razmak između prepreka je konstantan (space).
Koordinata y gornje prepreke se generiše random i od nje zavisi položaj donje prepreke. Pomoću metode move, prepreke se 
pomjeraju u lijevu stranu konstantnom brzinom.

Klasa Frame nasledjuje klasu JFrame. Klasa ima jednu metodu private JMenuBar initMenu() koja pravi meni igrice.

