# Presentations  

Prezentacje na warsztaty "Android^2" na warsjawa.pl.

Oparte na Reveal.JS by Hakim El Hattab, http://hakim.se.


## Feedback z Touk edition:

* nie można polegać na tym, że ludzie będą aktywnie uczestniczyć - trzema mieć plan pt. "kodujemy na ekranie i opowiadamy"
* już teraz mamy bardzo dużo materiału i warto raczej ograniczać, niż rozszerzać - myślę, że poradzimy sobie z tym ucinając kodowanie nudnych rzeczy przeklejaniem
* nie możemy polegać na tym, że wszyscy uczestnicy będą mieli doświadczenie w androidzie - krótki wstęp powinien być
* warto skupić się na używaniu bibliotek, a bebechy pokazywać tylko w ramach pytań, bo to dla wielu osób mało interesujące
* skoro pokazujemy konkretne biblioteki, to może warto o alternatywach tylko wspomnieć, a nie robić szerszego porównania


## Treść

* prezentacja główna (kim jesteśmy, co będziemy robić, kolejne kroki warsztatu, sprawy organizacyjne)
* o architekturze androidowych aplikacji
* o Google Annotations Gallery ;)
* o maps/location api
* o android maps extensions (jeśli używamy, ja bym użył)
* o retroficie, okhtml i picasso (można rozdzielić, ale w sumie są to bliskie frameworki)
* o otto i wzorcu EventBus
* o daggerze i wzorcu DependencyInjection


## Zadania:

0. Co robimy:
* jakie najczęstsze typy aplikacji piszemy? dlaczego takie?
* co szczególnego jest w Androidzie?
- refleksji mniej
- czas startu ma bardzo duże znaczenie
- automatyczne zarządzanie pamięcią
- programiści rzadko stosują sprawdzone zasady z enterprise 
(poniekąd przez brak tych zasad w dokumentacji)

1. Architektura aplikacji:
* jakie główne Activity możemy wydzielić?
* jakie główne Fragmenty możemy wydzielić?
* czy planujemy wykorzystać jakieś Servisy? jakie, dlaczego?
* modularyzacja - jakie klasy nieandroidowe się nam przydadzą?
* które klasy powinny być singletonami? które powinny mieć spięty czas życia?
* jakie klasy powinniśmy wydzielić w podstawowych komponentach? 
- lista: ListView-Adapter-DataSource-CellView
- lista: adapter adapterów

2. Komunikacja między obiektami - EventBus:
* jak skomunikować Activity z Fragmentem? a jak Service z Fragmentem?
* w jaki sposób Fragmenty powinny rozmawiać między sobą?
* jak skomunikować customowy View z innym customowym View?
* listener vs eventbus - co zyskujemy, co tracimy?
* dlaczego nie LocalBroadcastManager? czy jednolity interfejsu w komunikacji wewnątrz i na zewnątrz aplikacji ma sens?

3. Modularyzacja - Dagger:
* w jaki sposób będziemy zapewniać dostęp do modułów w różnych częściach aplikacji? singleton i getInstance()? Application jako globalny scope?
* dlaczego warto korzystać z dependency injection?

4. Narzędzia - OkHttp, Retrofit, Picasso:
* 

5. Możliwości - Maps API:
* 


## Timeboxing:

* 13:30-14:45 - 
* 14:45-15:15 - przerwa
* 15:15-16:30 -
* 16:30-17:00 - przerwa
* 17:00-18:15 -
* 18:15-18:45 - przerwa
* 18:45-20:00 - 


## Konwencja

* tekst   -> html lub markdown, bez różnicy, ale fragmenty tylko z htmlem działają
* style   -> css/android2.css
* obrazki -> img/*.png

poszczególne prezentacje w osobne [nazwa].html, a nawigację wewnątrzną zrobimy za pomocą linków -> tak będzie łatwiej żonglować kolejnością, coś omijać itp.


## Instalacja

Fragment oryginalnego README dotyczący instalacji:

## Installation

The **basic setup** is for authoring presentations only. The **full setup** gives you access to all reveal.js features and plugins such as speaker notes as well as the development tasks needed to make changes to the source.

### Basic setup

The core of reveal.js is very easy to install. You'll simply need to download a copy of this repository and open the index.html file directly in your browser.

1. Download the latest version of reveal.js from <https://github.com/hakimel/reveal.js/releases>

2. Unzip and replace the example contents in index.html with your own

3. Open index.html in a browser to view it


### Full setup

Some reveal.js features, like external markdown, require that presentations run from a local web server. The following instructions will set up such a server as well as all of the development tasks needed to make edits to the reveal.js source code.

1. Install [Node.js](http://nodejs.org/)

2. Install [Grunt](http://gruntjs.com/getting-started#installing-the-cli)

4. Clone the reveal.js repository
```
$ git clone git@github.com:hakimel/reveal.js.git
```

5. Navigate to the reveal.js folder
```
$ cd reveal.js
```

6. Install dependencies
```
$ npm install
```

7. Serve the presentation and monitor source files for changes
```
$ grunt serve
```

8. Open <http://localhost:8000> to view your presentation

You can change the port by using `grunt serve --port 8001`.


### Folder Structure
- **css/** Core styles without which the project does not function
- **js/** Like above but for JavaScript
- **plugin/** Components that have been developed as extensions to reveal.js
- **lib/** All other third party assets (JavaScript, CSS, fonts)

