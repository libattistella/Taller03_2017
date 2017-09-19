package com.example.lucas.workfromhome;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorLaboral extends BaseAdapter {

    private ArrayList<Trabajo> listaTrabajos;
    private LayoutInflater inflater;
    private Context context;

    public AdaptadorLaboral(Context ctx, List<Trabajo> items){
        super();

        this.context = ctx;
        this.listaTrabajos = new ArrayList<>(items);
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listaTrabajos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTrabajos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaTrabajos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DecimalFormat df = new DecimalFormat("#.##");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        View row=view;

        if(row == null) row = inflater.inflate(R.layout.content_ofertalaboral,viewGroup,false);
        OfertaViewHolder holder = (OfertaViewHolder) row.getTag();

        if(holder == null){
            holder = new OfertaViewHolder(row);
            row.setTag(holder);
        }

        Trabajo item = (Trabajo) getItem(i);

        holder.tvCategoria.setText(item.getCategoria().getDescripcion());
        holder.tvTrabajo.setText(item.getDescripcion());
        holder.tvHoras.setText("Horas: "+item.getHorasPresupuestadas());
        //holder.tvPrecio.setText("Max $/Hora: ");
        holder.tvPrecio.setText("Max $/Hora: "+df.format(item.getPrecioMaximoHora()));
        //holder.tvFecha.setText("Fecha Fin: ");
        holder.tvFecha.setText("Fecha Fin: "+sdf.format(item.getFechaEntrega()));
        //holder.cbEnIngles.setChecked(false);
        holder.cbEnIngles.setChecked(item.getRequiereIngles());

        switch(item.getMonedaPago()){
            case 1:
                holder.imgBandera.setImageResource(R.drawable.us);
                break;
            case 2:
                holder.imgBandera.setImageResource(R.drawable.eu);
                break;
            case 3:
                holder.imgBandera.setImageResource(R.drawable.ar);
                break;
            case 4:
                holder.imgBandera.setImageResource(R.drawable.uk);
                break;
            case 5:
                holder.imgBandera.setImageResource(R.drawable.br);
                break;
        }

        return row;
    }

    public ArrayList<Trabajo> getListaTrabajos() {
        return listaTrabajos;
    }

    class OfertaViewHolder{
        TextView tvCategoria;
        TextView tvTrabajo;
        TextView tvHoras;
        TextView tvPrecio;
        TextView tvFecha;
        ImageView imgBandera ;
        CheckBox cbEnIngles;

        public OfertaViewHolder(View v){
            tvCategoria = (TextView) v.findViewById(R.id.textView_Categoria);
            tvTrabajo = (TextView) v.findViewById(R.id.textView_Trabajo);
            tvHoras = (TextView) v.findViewById(R.id.textView_Horas);
            tvPrecio = (TextView) v.findViewById(R.id.textView_Precio);
            tvFecha= (TextView) v.findViewById(R.id.textView_Fecha);
            imgBandera = (ImageView) v.findViewById(R.id.imageView_Bandera);
            cbEnIngles = (CheckBox) v.findViewById(R.id.checkBox_EnIngles);
        }

    }
}
