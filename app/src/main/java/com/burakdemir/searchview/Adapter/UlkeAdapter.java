package com.burakdemir.searchview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.burakdemir.searchview.Model.Ulke;
import com.burakdemir.searchview.R;

import java.util.ArrayList;

public class UlkeAdapter extends RecyclerView.Adapter<UlkeAdapter.MyViewHolder> implements Filterable{

    Context context;
    LayoutInflater inflater;
    ArrayList<Ulke> ulkelerListFull; // bu lazım çünkü diğeri eksik bir listenin nesnesine referans edecek
    ArrayList<Ulke> filteredUlkeList;

    public UlkeAdapter(Context context, ArrayList<Ulke> ulkelerListFull) {
        this.context = context;
        this.ulkelerListFull = ulkelerListFull;
        this.filteredUlkeList = ulkelerListFull;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.tek_satir, parent, false);

        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.ivBayrak.setImageResource(filteredUlkeList.get(position).getBayrakResmi());
        holder.tvUlkeAdi.setText(filteredUlkeList.get(position).getUlkeAdi());
    }

    @Override
    public int getItemCount() {
        return filteredUlkeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBayrak;
        TextView tvUlkeAdi;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivBayrak = itemView.findViewById(R.id.ivBayrak);
            tvUlkeAdi = itemView.findViewById(R.id.tvUlkeAdi);
        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {

            // işlem worker thread'de gerçekleşir
            // kullanıcı harf girmiştir
            // diyelim "b" harfi girdi o zaman harfi b harfi içeren tüm objeleri Ulke tipindeki queryListe ekle
            // Örnekte diyelim ki başkentte olsun ve aynı zamanda başkente göre de arama yapılabilsin onu yapabilmek için de aşağıdakini uygula
            // if (ulke.getUlkeAdi().toLowerCase().contains(query.toLowerCase() || ulke.getBaskentAdi.toLowerCase.contains(query.toLowerCase())))
            // burası için FilterResult döndürğümüzde publish metotu tetiklenir ve return edilen değer oraya parametre ile gelir
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String query = charSequence.toString();
                ArrayList<Ulke> queryList = new ArrayList<>();

                // boş ise zaten queryListe full listeyi ata
                if (query.isEmpty()) {

                    queryList = ulkelerListFull;
                }
                else {

                    // ulkelerListFull'ü tamamen gez eşleşenleri queryList'e ekle
                    for (Ulke ulke : ulkelerListFull) {

                        if (ulke.getUlkeAdi().toLowerCase().contains(query.toLowerCase())) {

                            queryList.add(ulke);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = queryList;

                return filterResults;
            }

            // performFilterde döndürülen liste burada filterResults olarak gelir
            // filter edilen listeye gelen parametrenin referansını veririz
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                // burada listeye clear diyemezsin çünkü aynı nesnelere referans ediyolar
                // ve işlem async olduğu için filterResult içerisini de silmiş olursun (aynı nesneye referans ediyor)
                // burada değerler ArrayList içerisine atılmıyor filterResult referansı benim filterUlkeList'ime atanıyor böylece aynı objeye referans ediyolar
                filteredUlkeList = (ArrayList<Ulke>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
