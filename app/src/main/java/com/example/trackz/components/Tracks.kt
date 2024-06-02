package com.example.trackz.components

import com.example.trackz.R


class Track(val description: String, var favourite: Boolean, val image: Int)

var Tracks = hashMapOf(
    "Morskie Oko" to Track("Morskie oko to znana atrakcja turystyczna dla wielu Polakow.", false, R.drawable.morskie_oko),
    "Tatry" to Track("Tatry to najwyzsze gory w Polsce.", false, R.drawable.tatry),
    "Wieliczka" to Track("Kopalnia soli w Wieliczce to jedna z najwiekszych atrakcji turystycznych w Polsce.", false, R.drawable.wieliczka),
    "Rysy" to Track("Rysy to najwyzszy szczyt w Polsce.", false, R.drawable.rysy),
    "Orle gniazda" to Track("Orle gniazda to jedna z najbardziej znanych atrakcji turystycznych w Polsce.", false,  R.drawable.orle_gniazda),
    "Kraina Otwartych Okiennic" to Track("Kraina Otwartych Okiennic to szlak tworzony przez 3 unikalne wsie na Podlasiu - Trześciankę, Soce i Puchły.", false, R.drawable.kraina_otwartych_okiennic),
    "Bieszczady" to Track("Bieszczady to góry w południowo-wschodniej Polsce.", false, R.drawable.bieszczady),
    "Szlak Piastowski" to Track("Szlak Piastowski to niezwykle atrakcyjna trasa turystyczna łącząca trzy grody stołeczne z okresu wczesnopiastowskiego: Poznan, Gniezno i Kruszwica.", false, R.drawable.szlak_piastowski),
    "Szlak Cysterski" to Track("Szlak Cysterski to wyjątkowy szlak turystyczny, powstały na terytorium Europy, obejmujący znaczny obszar Polski, szczególnie w zachodniej i środkowej jej części.", false, R.drawable.szlak_cysterski),
    "Droga Stu Zakretow" to Track("Droga Stu Zakrętów zwana też Szosą Stu Zakrętów to bardzo popularna trasa wiodąca przez Góry Stołowe.", false, R.drawable.droga_stu_zakretow),
    "Szlak Tatarski" to Track("Szlak Tatarski to ciekawy szlak turystyczny w Polsce wschodniej, na Podlasiu.", false, R.drawable.szlak_tatarski),
    )