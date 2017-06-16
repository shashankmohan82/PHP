//package haptik.producthunt.task.producthuntplus;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        final EditText editText = (EditText) findViewById(R.id.editText);
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        ProductHuntAPI api = new ProductHuntAPI();
//        progressBar.setVisibility(View.VISIBLE);
//        editText.setVisibility(View.GONE);
//        api.apiService().getPosts().enqueue(new Callback<Posts>() {
//            @Override
//            public void onResponse(Call<Posts> call, Response<Posts> response) {
//                progressBar.setVisibility(View.GONE);
//                editText.setVisibility(View.VISIBLE);
//                editText.setText(response.body().getPosts().get(0).getId()+"");
//                Toast.makeText(MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Posts> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//}
