package br.com.etecia.listaprodutosvolleyrecview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdutoAdapter
        extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> listaProdutos;

    public ProdutoAdapter(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_produto,
                        parent,
                        false
                );

        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ProdutoViewHolder holder,
            int position) {

        Produto produto = listaProdutos.get(position);

        holder.txtNome.setText(produto.getNome());

        holder.txtPreco.setText(
                "R$ " + produto.getPreco()
        );

        if (produto.getPreco() > 100) {
            holder.txtPreco.setTextColor(Color.RED);
        } else {
            holder.txtPreco.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> {

            Toast.makeText(
                    v.getContext(),
                    "Produto: " + produto.getNome(),
                    Toast.LENGTH_SHORT
            ).show();

        });
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    static class ProdutoViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtNome;
        TextView txtPreco;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(
                    R.id.txtNome
            );

            txtPreco = itemView.findViewById(
                    R.id.txtPreco
            );
        }
    }
}