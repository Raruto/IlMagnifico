# IlMagnifico

### Manuale Utente:
1) [**Requisiti minimi dell’utente**](#10-requisiti-minimi-dellutente)

2) [**Utilizzo del gioco da Linea di Comando (CLI)**](#20-utilizzo-del-gioco-da-linea-di-comando-cli)
   1) [**Connessione**](#21-connessione)
   2) [**Turno del giocatore**](#22-turno-del-giocatore)
   3) [**Mercato**](#23-mercato)
   4) [**Raccolto**](#24-raccolto)
   5) [**Produzione**](#25-produzione)
   6) [**Palazzo del Consiglio**](#26-palazzo-del-consiglio)
   7) [**Torre**](#27-torre)
   8) [**Famigliare**](#28-famigliare)
   9) [**Sostegno dell Chiesa**](#29-sostegno-della-chiesa)
   10) [**Chat**](#210-chat)
   11) [**Board**](#211-board)
   12) [**Dash**](#212-dash)
   13) [**Cards**](#213-cards) <a href="http://www.craniocreations.it/prodotto/lorenzo-il-magnifico/"><img src="https://raruto.github.io/IlMagnifico/wiki-assets/ScatolaDelGioco.jpg" width="400" alt="Scatola del Gioco" align="right" /></a>

3) [**Utilizzo del gioco da Interfaccia Grafica (GUI)**](#30-utilizzo-del-gioco-da-interfaccia-grafica-gui)
   1) [**Connessione**](#31-connessione)
   2) [**Tabellone**](#32-tabellone)
   3) [**Plancia**](#33-plancia)
   4) [**Avversari**](#34-avversari)   

4) [**Le Scomuniche**](#40-le-scomuniche)

5) [**Fine Del Gioco**](#50-fine-del-gioco)

### 1.0 Requisiti minimi dell’utente

Si presuppone che l’utente conosca completamente le [regole](https://raruto.github.io/IlMagnifico/IlMagnifico/res/rules/regole_del_gioco.pdf) del gioco da tavolo [“Lorenzo Il
Magnifico”](http://www.craniocreations.it/prodotto/lorenzo-il-magnifico/), prodotto dalla [Cranio Creations](http://www.craniocreations.it/)


### 2.0 Utilizzo del gioco da Linea di Comando (CLI)

### 2.1 Connessione

Per una corretta configurazione della connessione è richiesto l’avvio precedente della
corrispettiva applicazione Server.
All’avvio del programma Client (CLI) viene chiesto quale connessione utilizzare: se
l’utente vuole utilizzare la connessione realizzata con RMI digita `“rmi”` e preme invio, se
vuole utilizzare la connessione realizzata tramite socket digita `“socket”` e preme invio.
Tali stringhe non sono suscettibili alle maiuscole.

### 2.2 Turno del giocatore

Terminate le operazioni di connessione, viene notificato agli utenti se la partita è stata
creata tramite opportuno messaggio. Da questo momento ogni fase di gioco e azione
dei giocatori sono notificate da messaggi preceduti da una stringa racchiusa tra
parentesi quadre. Tale stringa indica l’utente che ha eseguito l’azione o è uguale a
`“GAME”` se si tratta di una fase di gioco.
Ad ogni giocatore viene notificato di chi è il turno e le azioni disponibili.
Tali azioni sono indicate tra parentesi in minuscolo e, ad eccezione della azione `“chat”`,
vengono eseguite solo dal giocatore di turno. Se un giocatore non di turno cerca di
eseguire una di queste azioni, gli viene notificato un messaggio di errore. Allo stesso
modo, se il giocatore di turno cerca di effettuare una azione che secondo le regole non è
attuabile, viene notificato un messaggio di errore che ne indica la causa del tale.

Per selezionare una delle azioni disponibili, il giocatore di turno scrive il nome
dell’azione e preme invio. Si sottolinea che se l’azione non è riconosciuta, verrà chiesto
ancora all’utente di inserire una scelta tra le azioni disponibili. A dipendere dalla azione,
vengono stampate le informazioni dello stato del gioco utili per l’azione stessa e le
opzioni ulteriori che l’utente può scegliere.

## Le Azioni `“action”`

### 2.3 Mercato

Nel caso venga selezionato `“Market”` vengono stampate le zone del mercato: esse
varranno null se attualmente nessun famigliare è presente nella zona, altrimenti verrà
segnalato quale famigliare è presente e a quale giocatore appartiene. Viene poi chiesto
al giocatore quale zona si vuole occupare e con quale famigliare.

### 2.4 Raccolto

Nel caso venga selezionata l’azione `“Harvest”` vengono stampate le due zone del
raccolto: la prima zona, quella rotonda, conterrà null se non vi è presente nessun
famigliare, la seconda zona, quella ovale, non conterrà niente se non è presente nessun
famigliare. Come nella zona del mercato, se è presente un famigliare, viene notificato
nella stampa.
A seguire viene chiesto all’utente quale delle due zone vuole occupare, con quale
famigliare e, se presenti, se vuole attivare i singoli effetti permanenti delle carte
Territorio in proprio possesso.

### 2.5 Produzione

Il funzionamento dell’azione `“Production”` è esattamente uguale a quello dell’azione
Raccolto, con l’unica differenza che viene chiesto se si vuole attivare i singoli effetti
permanenti delle carte edificio anziché delle carte territorio.

### 2.6 Palazzo Del Consiglio

Nel caso venga selezionata l’azione `“CouncilPalace”` verranno stampati i famigliari
attualmente nella zona e verrà chiesto quale dei propri famigliari si vuole posizionare
nella zona. Verrà poi chiesta quale effetto del privilegio del consiglio eseguire.

### 2.7 Torre

Se viene selezionata l’azione `“Tower”`, vengono stampate le torri e le carte posizionate
su esse. Nel caso sia presente un famigliare in un piano della torre, verranno stampate le
informazioni su quest’ultimo.
Successivamente, viene chiesto al giocatore su quale torre vuole posizionare il proprio
famigliare. Data la scelta, vengono stampate le carte sulla torre in questione ed i relativi
costi e viene chiesto all’utente quale dei piani della torre si vuole occupare.
Proseguendo dopo la scelta, se la carta possiede costi alternativi, viene chiesto
all’utente quale pagare, altrimenti avviene il pagamento in automatico.

### 2.8 Famigliare

Se viene selezionata l’azione `“Familiar”`, viene chiesto all’utente a quale famigliare vuole
aumentare il valore pagando servitori e successivamente quanti servitori vuole pagare.
Da notare che tale azione, a differenza delle azioni standard, non prevede il passaggio
del turno al compimento della stessa.

### 2.9 Sostegno Della Chiesa

Tale azione diventa disponibile solamente alla fine di ogni periodo. Quando selezionata,
se la fase di gioco è effettivamente il Rapporto col Vaticano, viene chiesto all’utente se
vuole sostenere la Chiesa oppure no. A seconda della risposta e delle disponibilità dei
punti fede del giocatore, vengono eseguite le istruzioni corrispondenti.

## Il comando `“chat”`

### 2.10 Chat

L’azione chat si differenzia dalle altre azioni in quanto può essere utilizzata dagli utenti
in qualunque momento. Come suggerisce il nome, permette ai giocatori di inviare un
messaggio comune.

## Il comando `“board”`

### 2.11 Board

Se selezionata l’azione `“board”`, vengono stampate le informazioni relative al tabellone
di gioco secondo le convenzioni descritte per le azioni `“action”`.

## Il comando `“dash”`

### 2.12 Dash

Se selezionata l’azione `“dash”`, vengono stampate le informazioni relative alla propria
plancia giocatore. Viene chiesto anche se si vuole stampare le informazioni relative alle
plance degli altri giocatori.

## Il comando `“cards”`

### 2.13 Cards

Se selezionata l’azione `“cards”`, vengono stampate le informazioni relative a tutte le
carte presenti nel gioco.

### 3.0 Utilizzo del gioco da Interfaccia Grafica (GUI)

### 3.1 Connessione

Per una corretta configurazione della connessione è richiesto l’avvio precedente della
corrispettiva applicazione Server.
All’avvio del programma Client (GUI) viene chiesto quale connessione utilizzare: `“rmi”`,
oppure `“socket”`, ed il nome utente da utilizzare per il giocatore.

Una volta portata a termine la
connessione, il programma rimarrà in
attesa della notifica da parte del
Server dell’aggiunta di altri giocatori e
conseguente inizio della partita

<sub>**UsernameFrame**, schermata usata per il login e attesa inizio partita</sub>
![UsernameFrame](https://raruto.github.io/IlMagnifico/wiki-assets/UsernameFrame.jpg)

### 3.2 Tabellone

Il tabellone è il componente principale della partita dove sono presenti la maggior parte
delle azioni di gioco che un giocatore può svolgere posizionando un famigliare
all’interno della partita. Per posizionare un famigliare è necessario prima selezionarlo
nella plancia con un click ed in seguito selezionare la zona dove spostarlo, sempre con
un click. Inoltre è possibile prendere visione delle carte sviluppo, associate alle torri, e
delle eventuali scomuniche, associate ai giocatori.

<sub>**GUI**, schermata di esempio di una configurazione del tabellone nel corso di una partita</sub>
![GUI](https://raruto.github.io/IlMagnifico/wiki-assets/GUI.jpg)

### 3.3 Plancia

La plancia è il componente che contiene le informazioni relative alle carte e alla riserva
del giocatore.
In quest’area un giocatore può
eseguire un’azione `“famigliare”`
incrementando di uno il valore
di una pedina (eseguendo 1
click su servitore e
successivamente 1 click sulla
pedina desiderata).

<sub>**Plancia**, esempio di una configurazione della plancia giocatore</sub>
![Plancia](https://raruto.github.io/IlMagnifico/wiki-assets/Plancia.jpg)

### 3.4 Avversari

Componente del gioco che non fa altro che esporre le plance degli avversari (riserva,
carte sviluppo). Non e possibile prendere visione dei famigliari non ancora posizionati.

<sub>**PlanciaAvversario**, esempio di una configurazione della plancia di un giocatore avversario</sub>
![PlanciaAvversario](https://raruto.github.io/IlMagnifico/wiki-assets/PlanciaAvversario.jpg)

### 4.0 Le Scomuniche

Al giocatore viene notificata la scomunica solamente quando essa viene applicata la prima
volta dopo un rapporto del Vaticano. Nella modalità linea di comando, è possibile
consultare le scomuniche della partita all’interno del comando `“dash”` (scomuniche
associate ai giocatori) o `“board”` (scomuniche in uso); il programma attua
automaticamente tutti i malus derivanti dalle tessere scomunica.

### 5.0 Fine Del Gioco

Alla fine della partita, il programma calcola la classifica finale basata sui punti vittoria in
possesso dei giocatori, calcolando tutti i bonus derivanti da carte e i malus derivanti dalle
tessere scomunica, nonché i punti vittoria ricevuti in base alle risorse in proprio possesso.
Tale classifica viene poi inviata a tutti i giocatori.

---

**Risorse utili:** [**Manuale utente.pdf**](https://raruto.github.io/IlMagnifico/IlMagnifico/res/docs/manuale_utente.pdf) **-** [**Regole del gioco:**](https://raruto.github.io/IlMagnifico/IlMagnifico/res/rules/regole_del_gioco.pdf) [**[EN]**](https://raruto.github.io/IlMagnifico/IlMagnifico/res/rules/regole_del_gioco_in_Inglese.pdf) **-** [**Documento di progetto.pdf**](https://raruto.github.io/IlMagnifico/IlMagnifico/res/docs/documento_di_progetto.pdf) **-** [**UML.mdj**](https://raruto.github.io/IlMagnifico/IlMagnifico/res/uml/LorenzoIlMagnifico.mdj)

---

**Contributori:** [**CharlieChaplin1947**](https://github.com/CharlieChaplin1947), [**Raruto**](https://github.com/Raruto)
