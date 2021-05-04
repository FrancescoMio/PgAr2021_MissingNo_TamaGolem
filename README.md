# FrancescoMio-PgAr2021_MissingNo_TamaGolem

Il programma che dovete implementare permette a due giocatori di sfidarsi in un combattimento fra
TamaGolem. Per “giocare”, l’utente deve comunicare al programma l’inizio di una nuova partita. Ogni
partita si divide in tre fasi:
1. La prima è di setup, non richiede l’interazione dell’utente e il risultato non viene rivelato. Serve a
stabilizzare gli equilibri fra le forze del Mondo, ossia a stabilire il funzionamento delle interazioni fra
gli elementi per quella specifica partita.
2. La seconda comprende lo Scontro fra i due giocatori, secondo meccaniche che verranno spiegate
nelle diapositive successive.
3. La fase finale prevede la dichiarazione del vincitore, e l’eventuale rivelazione dell’Equilibrio del Mondo
(vedi slide successive).
Il programma, infine, chiede all’utente se vuole iniziare una nuova partita o se intende terminare
l’esecuzione dello stesso.

Una volta definiti gli elementi in gioco, l’Equilibrio viene generato in maniera pseudo-casuale, facendo
attenzione che vengano rispettate le seguenti regole:
● Dati due elementi differenti, la loro interazione non è mai nulla.
● In particolare, per ciascuna coppia di elementi, uno solo dei due svolgerà il ruolo di elemento forte,
mentre l’altro sarà l’elemento debole. Questi ruoli non si modificano nel corso della partita.
● Dati due elementi uguali, la loro interazione è sempre nulla.
● L’interazione di due elementi è caratterizzata da uno specifico valore, detto potenza dell’interazione
(o anche solo potenza). Anch’esso non si modifica durante la partita.
● Per ciascun elemento, la somma delle potenze delle interazioni in cui tale elemento è forte è uguale
alla somma delle potenze delle interazioni in cui è debole (proprietà fondamentale dell’Equilibrio).

Una volta che è stato costruito l’Equilibrio comincia la fase dello Scontro. Per la fase dello scontro, in
generale succede che:
● Ogni giocatore ha a disposizione una quantità G fissata di TamaGolem, che può evocare nel corso
dello Scontro.
● Ogni TamaGolem può ingurgitare fino a P pietre degli elementi, ossia pietre speciali caratterizzate dal
loro legame con uno (e un solo) degli N elementi.
● Può scendere in campo solo un TamaGolem per giocatore alla volta. I due TamaGolem in campo si
sfideranno scagliando uno contro l’altro pietre degli elementi.
● Due pietre scagliate l’una contro l’altra scatenano il potere dei relativi elementi, che interagiscono
secondo l’Equilibrio generato nella Fase 1. L’elemento debole causa dei danni al TamaGolem che l’ha
scagliato.
● Quando un TamaGolem subisce un certo numero massimo di danni, denominato Vita del
TamaGolem, viene eliminato dallo scontro. Il rispettivo giocatore deve quindi evocarne un altro.
● Chi perde G TamaGolem è considerato sconfitto. L’altro giocatore è il vincitore.


Lo scontro ha la seguente struttura:
1. Viene decretato il primo giocatore, d’ora in poi denotato con A.
2. Il giocatore A schiera il primo TamaGolem, seguendo le regole per l’evocazione di un TamaGolem
(spiegate più avanti).
3. Dopodichè, il giocatore B fa lo stesso.
4. I due TamaGolem cominciano un turno:
○ Essi scagliando la pietra del turno l’uno contro l’altro: i due elementi interagiscono.
○ Il TamaGolem che ha scagliato l’elemento debole fra i due subisce un numero di danni pari alla
potenza dell’interazione fra i due elementi, e la sua Vita diminuisce.
5. Se un TamaGolem ha raggiunto un valore di Vita nullo o negativo, allora:
○ Quel TamaGolem è eliminato dal gioco.
○ Se possibile, il relativo giocatore segue la fase di evocazione del nuovo TamaGolem.
Altrimenti, se il TamaGolem ferito ha ancora Vita positiva:
○ Viene segnalato ai giocatori il numero di danni inflitti al TamaGolem in quel turno.
Se nessun giocatore è stato decretato vincitore, si torna al passo 4.


La procedura di evocazione di un TamaGolem è una sottofase della fase 2 che ricorre più volte durante lo
scontro. In generale l’evocazione interrompe lo Scontro ogni qualvolta sia necessario schierare in campo un
nuovo TamaGolem da parte di un giocatore, evento che si verifica quando il TamaGolem precedente del
giocatore è eliminato dallo Scontro.
L’evocazione si verifica solo se il giocatore ha ancora TamaGolem disponibili, ossia il numero di TamaGolem
eliminati è strettamente inferiore a G. In caso affermativo:
1. Un nuovo TamaGolem viene selezionato per la fase dell’evocazione; la sua Vita viene inizializzata al
valore massimo V.
2. Il giocatore seleziona esattamente P pietre degli elementi da far mangiare al TamaGolem, fra quelle a
sua disposizione. Le pietre selezionate verranno scagliate ciclicamente finché il TamaGolem resterà in
vita.
3. Il TamaGolem viene schierato in campo, l’evocazione termina e lo Scontro può procedere.
In caso negativo, invece:
1. Il giocatore con G TamaGolem eliminati è considerato sconfitto.
2. L’altro giocatore è decretato vincitore.



All’eliminazione dell’ultimo TamaGolem del giocatore sconfitto, si entra nella fase finale.
Questa fase prevede la dichiarazione del vincitore della partita, secondo le regole mostrate nelle slide
precedenti, e la rivelazione dell’intero Equilibrio.
La rivelazione può essere fatta (ad esempio) visualizzando la lista di tutte le interazioni possibili fra i vari
elementi, oppure attraverso una matrice. La scelta è libera, ma deve prevedere unicamente output da linea
di comando.
Note:
● Deve essere possibile incominciare una nuova partita senza dover azzerare l’esecuzione del
programma.
● Non è richiesta alcuna memorizzazione delle partite passate, dei giocatori o dei vecchi Equilibri
generati.
● Non è richiesto il calcolo di alcun punteggio per i giocatori, ma solamente chi è il giocatore vincente
e chi quello sconfitto.
