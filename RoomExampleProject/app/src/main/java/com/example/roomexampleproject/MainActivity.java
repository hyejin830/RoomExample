package com.example.roomexampleproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userIdEditText;
    private EditText userFirstNameEditText;
    private EditText userLastNameEditText;
    private Button allInsertButton;
    private Button selectAllButton;
    private TextView selectResultTextView;

    private AppDatabase appDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        appDatabase = appDatabase.getDatabase(this);
    }

    private void initView() {
        userIdEditText = findViewById(R.id.et_id);
        userFirstNameEditText = findViewById(R.id.et_first_name);
        userLastNameEditText = findViewById(R.id.et_last_name);
        allInsertButton = findViewById(R.id.btn_insert_all);
        selectAllButton = findViewById(R.id.btn_select_id);
        selectResultTextView = findViewById(R.id.tv_select_user);

        allInsertButton.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_all:
                insertAllUser(Integer.valueOf(userIdEditText.getText().toString()),
                        userFirstNameEditText.getText().toString(),
                        userLastNameEditText.getText().toString());
                break;
            case R.id.btn_select_id:
                getUsers();
                break;
        }
    }

    private void getUsers() {
        appDatabase.userDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                Log.d("HJ", "accept");
                onUsersLoaded(users);
            }
        });
    }

    private void insertAllUser(final int userId, final String firstName, final String lastName) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                User user = new User(userId, firstName, lastName);
                appDatabase.userDao().insertAll(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("HJ", "onSubscribe");
            }

            @Override
            public void onComplete() {
                Log.d("HJ", "onComplete");
                onUserAdded();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("HJ", "onError");

            }
        });

    }


    public void onUserAdded() {
        Toast.makeText(this, getString(R.string.text_add_complete), Toast.LENGTH_LONG).show();
    }


    public void onUsersLoaded(List<User> users) {
        String result = "";
        for (int i = 0; i < users.size(); i++) {
            String id = "id는 " + String.valueOf(users.get(i).getUid()) + "\n";
            String first = "first name은 " + users.get(i).getFirstName() + "\n";
            String last = "last name은 " + users.get(i).getLastName() + "\n";

            result = result + id + first + last + "\n";
        }

        selectResultTextView.setText(result);
    }
}

