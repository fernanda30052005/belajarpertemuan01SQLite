package com.nanda.belajarpertemuan01sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvfavorit;
    FloatingActionButton Floating;
    DatabaseHandler hendler;

    List<String> listFavorit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvfavorit = findViewById(R.id.lvfavorit);
        Floating  = findViewById(R.id.Floating);
        hendler = new DatabaseHandler(this);

       //method utk menampilkan data
        TampilkanSemuaData();
        lvfavorit.setOnItemClickListener(((adapterView, view, i, l) -> {
            String data = listFavorit.get(i);

            //kirim data ke dialogUpdate
            bukaDialogUpdate(data);
        }));

        Floating.setOnClickListener(view -> bukaDialogTambah());



    }

    private void bukaDialogUpdate(String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Menu Favorit");
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.frm_update,null);

        builder.setView(dialogView);

        EditText etNama = dialogView.findViewById(R.id.et_nama);
        EditText etNim = dialogView.findViewById(R.id.et_nim);
        EditText etProdi = dialogView.findViewById(R.id.et_prodi);
        Button btnUpdate= dialogView.findViewById(R.id.btn_update);
        Button btnHapus = dialogView.findViewById(R.id.btn_Hapus);

        //tampilkan item yang dipilih dari ist view ke dalam edittext
        etNama.setText(data);

        AlertDialog dialog = builder.create();

        btnUpdate.setOnClickListener(view -> {
            if(etNama.getText().toString().trim().length() ==0){
                etNama.setError("HARUS DIISI");
                return;
            }
            simpanData(etNama.getText().toString());
            dialog.dismiss();
            TampilkanSemuaData();
        });
        dialog.show();
    }

    private void TampilkanSemuaData() {
        listFavorit = hendler.tampilSemua();
        //siapkan adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listFavorit
        );
        //hubungkan adapter dengan listview
        lvfavorit.setAdapter(adapter);
    }

    private void bukaDialogTambah(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Menu Favorit");
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.frm_tambah,null);

        builder.setView(dialogView);

        EditText etNama = dialogView.findViewById(R.id.et_nama);
        EditText etNim = dialogView.findViewById(R.id.et_nim);
        EditText etProdi = dialogView.findViewById(R.id.et_prodi);
        Button btnSimpan= dialogView.findViewById(R.id.btn_simpan);

        AlertDialog dialog = builder.create();

        btnSimpan.setOnClickListener(view -> {
            if(etNama.getText().toString().trim().length() ==0){
                etNama.setError("HARUS DIISI");
                return;
            }
            simpanData(etNama.getText().toString());
            dialog.dismiss();
            TampilkanSemuaData();
        });
        dialog.show();
    }

    private void simpanData(String nama) {
        hendler.simpan(nama);
    }
}