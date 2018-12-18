package com.burakdemir.searchview.Model;

import android.content.Context;

import com.burakdemir.searchview.R;

import java.util.ArrayList;

public class Ulke {

    private int bayrakResmi;
    private String ulkeAdi;

    public int getBayrakResmi() {
        return bayrakResmi;
    }

    public void setBayrakResmi(int bayrakResmi) {
        this.bayrakResmi = bayrakResmi;
    }

    public String getUlkeAdi() {
        return ulkeAdi;
    }

    public void setUlkeAdi(String ulkeAdi) {
        this.ulkeAdi = ulkeAdi;
    }

    public static ArrayList<Ulke> getUlkeler(Context context) {

        ArrayList<Ulke> ulkeList = new ArrayList<>();

        int[] bayrakResimleri = {R.drawable.afganistan, R.drawable.andorra, R.drawable.angola, R.drawable.anguilla, R.drawable.antartika,
                                 R.drawable.antigua_ve_barbuda, R.drawable.arjantin, R.drawable.arnavutluk, R.drawable.aruba,
                                 R.drawable.avustralya, R.drawable.avusturya, R.drawable.azerbaycan, R.drawable.banglades, R.drawable.barbados,
                                 R.drawable.belcika, R.drawable.birlesik_arap_emirlikleri, R.drawable.bosna_hersek, R.drawable.burkina_faso};

        String[] ulkeAdlari = {"Afganistan", "Andorra", "Angola", "Anguilla", "Antartika", "Antigua ve Barbuda", "Arjantin", "Arnavutluk",
                               "Aruba", "Avustralya", "Avusturya", "Azerbaycan", "Bangladeş", "Barbados", "Belçika", "Birleşik Arap Emirlikleri",
                               "Bosna Hersek", "Burkina Faso"};

        for (int i = 0; i < ulkeAdlari.length; i++) {

            Ulke ulke = new Ulke();

            ulke.setBayrakResmi(bayrakResimleri[i]);
            ulke.setUlkeAdi(ulkeAdlari[i]);

            ulkeList.add(ulke);
        }

        return ulkeList;
    }

}
