package com.example.myweatherbase.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorRecicler extends RecyclerView.Adapter<AdaptadorRecicler.ViewHolder> {
    private Root root;
    private LayoutInflater inflater;

    public AdaptadorRecicler(Context context, Root root){
        this.root = root;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdaptadorRecicler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecicler.ViewHolder holder, int position) {
        Date date = new Date((long)root.list.get(0).dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("E");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH:mm");

        holder.tvCielo.setText(root.list.get(0).weather.get(0).description);
        holder.tvTemperatura.setText(root.list.get(0).main.temp+"");
        holder.tvTempMax.setText(root.list.get(0).main.temp_max + "");
        holder.tvTempMin.setText(root.list.get(0).main.temp_min + "");
        holder.tvDay.setText(dateDayOfWeek.format(date));
        holder.tvDate.setText(dateDay.format(date));
        holder.tvHora.setText(dateHour.format(date));
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(0).weather.get(0).icon + Parameters.ICON_URL_POST, holder.image);
        //holder.image.setImageResource(Integer.parseInt(root.list.get(0).weather.get(0).icon));
    }

    @Override
    public int getItemCount() {
        return root.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDay;
        private TextView tvCielo;
        private TextView tvTemperatura;
        private TextView tvDate;
        private TextView tvHora;
        private TextView tvTempMax;
        private TextView tvTempMin;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvCielo = itemView.findViewById(R.id.tvCielo);
            tvTemperatura = itemView.findViewById(R.id.tvTemperatura);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHora = itemView.findViewById(R.id.tvHora);
            tvTempMax = itemView.findViewById(R.id.tvTempMax);
            tvTempMin = itemView.findViewById(R.id.tvTempMin);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}