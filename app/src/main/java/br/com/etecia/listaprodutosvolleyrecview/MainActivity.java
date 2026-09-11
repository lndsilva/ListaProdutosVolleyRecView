package br.com.etecia.listaprodutosvolleyrecview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private ProdutoAdapter adapter;
    private List<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCarregar =
                findViewById(R.id.btnCarregar);

        recyclerProdutos =
                findViewById(R.id.recyclerProdutos);

        listaProdutos = new ArrayList<>();

        adapter = new ProdutoAdapter(
                listaProdutos
        );

        recyclerProdutos.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerProdutos.setAdapter(adapter);

        btnCarregar.setOnClickListener(v -> {
            carregarProdutos();
        });
    }

    private void carregarProdutos() {

        String url =
                "https://sua-api.com/produtos";

        RequestQueue queue =
                Volley.newRequestQueue(this);

        JsonArrayRequest request =
                new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,

                        response -> {

                            listaProdutos.clear();

                            try {

                                for (int i = 0;
                                     i < response.length();
                                     i++) {

                                    JSONObject obj =
                                            response.getJSONObject(i);

                                    int id =
                                            obj.getInt("id");

                                    String nome =
                                            obj.getString("nome");

                                    double preco =
                                            obj.getDouble("preco");

                                    Produto produto =
                                            new Produto(
                                                    id,
                                                    nome,
                                                    preco
                                            );

                                    listaProdutos.add(produto);
                                }

                                adapter.notifyDataSetChanged();

                            } catch (JSONException e) {

                                Toast.makeText(
                                        this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();

                            }

                        },

                        error -> {

                            Toast.makeText(
                                    this,
                                    "Erro ao acessar API",
                                    Toast.LENGTH_LONG
                            ).show();

                        }
                );

        queue.add(request);
    }
}