package ru.mirea.boevas.employeedb;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import android.os.Bundle;
import android.util.Log;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        SuperheroDao superheroDao = db.superheroDao();
        Superhero superhero = new Superhero();
        superhero.id = 1;
        superhero.name = "Superman";
        superhero.strength = 100;
        superhero.age = 35;
        // запись сотрудников в базу
        superheroDao.insert(superhero);
        // Загрузка всех супергероев
        List<Superhero> superheroes = superheroDao.getAll();
        // Получение определенного супергероя с id = 1
        superhero = superheroDao.getById(1);
        // Обновление полей объекта
        superhero.strength = 90;
        superheroDao.update(superhero);
        Log.d(TAG, superhero.name + " " + superhero.strength);
    }
}






