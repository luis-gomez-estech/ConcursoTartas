package com.example.textinputlayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


public class MainActivity extends AppCompatActivity {

    boolean comprueba=false; //Creo una variable booleana que la inicializo en false para usarla depsues para determinar si el campo de nombre_concurso esta oculto o no
    // ya que si no se pulsa el check, seguira valiendo false y mas abajo le digo que si es false compruebe todos los campos menos el campo oculto (nombre_concurso)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tenemos que obtener 4 campos (Nombre, apellidos, edad y sexo)
        // y ademas si un checkbox esta activado, abrira otro campo que tambien hay que comprobar a la hora de inscribirse si esta relleno o no

        // por tanto dependiendo de si ese campo va a estar oculto o no,
        // cuando pulsemos el boton para inscribirse, si el campo esta oculto,
        // va a comprobar si todos los campos estan rellenos menos el campo oculto
        // pero si el check se ha seleccinado, entonces aparecera el campo oculto, entonces tenemos que comprobar si todos los campos incluido el campo oculto estan rellenos

        // Por tanto primero declaramos el TextInputLayout que va a estar oculto mientras no se tilde el check.
        // Tener en cuenta que le ponenos al principio la palabra final, para poder usarla despues.
        // Aunque tambien se puede hacer declarando fuera de onCreate  asi, EditText nombre_concurso; y no haria falta ponerle final.
        // asi ya dentro de onCreate pondriamos solo nombre_concurso = findViewById(R.id.campo_oculto);
        // pero en principio lo estoy haciendo toodo así, por tenerlo mas ordenado a la hora de entenderlo teniendo mas agrupado cada cosa.

        final EditText nombre_concurso = findViewById(R.id.campo_oculto); // Declaro el TextInputLayout, lo llamo nombre_concurso y le pongo el id del TextInputLayout al que hace referencia

        final String nombreConcursoString=nombre_concurso.getText().toString(); // Lo convierto a String

        nombre_concurso.setVisibility(View.GONE); // Ahora a la variable nombre_concurso, con setVisibility le digo que no "este" ese elemento, que no es lo mismo que invisible
        // por eso ponemos GONE para que no aparezca al abrir la app, pero si quisieramos ponerlo invisible, ponemos INVISIBLE

        // Ahora declaramos el checkbox y tambien le ponemos final

        final CheckBox checkBox1 = findViewById(R.id.primer_checkBox); // ponemos su id

        // Ahora le adjudico al checkbox que haga algo con  checkBox.setOnClickListener(new View.OnClickListener

        checkBox1.setOnClickListener(new View.OnClickListener() {

            // y lo que va a hacer es comprobar que si se hace click en checkbox pues que aparezca el EditText,
            // por tanto, necesitamos un metodo onclick para determinar si se ha hecho click que aparezca el EditText
            // y tambien que nos devuelva un valor true o false para dpeendendo de el comprobara todos los campos o todos menos el oculto
            @Override
            public void onClick(View v) {

                // SI el checkbox1 es clickeado, entonces el campo oculto (nombre_concurso) aparece y ademas la variable comprueba ahora vale true
                //de esta forma cuando el boton de inscribirse se pulse, comprobara si todos los campos estan rellenos incluido el campo nombre_concurso

                if(checkBox1.isChecked())
                {
                    nombre_concurso.setVisibility(View.VISIBLE); //For hiding

                    comprueba=true;

                }

                // pero si no es clickeado, el campo seguira oculto y la variable comprueba es false,
                // por tanto, ma abajo le digo que si es false, que el boton inscribirse compruebe todos los campos menos el oculto

                else
                {
                    nombre_concurso.setVisibility(View.GONE); //For showing
                    //EditText.setVisibility(View.VISIBLE); //For showing
                    comprueba=false;
                }

            }


        });


        // Ahora Declaro el boton Inscribirse que cuando se pulse va a obtener los datos en el Toast
            // y le digo el id del boton

            Button button = (Button)findViewById(R.id.inscribete);

             // Ahora le digo al boton que haga algo con  button.setOnClickListener(new View.OnClickListener()
            button.setOnClickListener(new View.OnClickListener() {

            // y lo que va a hacer es que cuando le demos a click  a va poner en Toast el valor de cada EditToast, tambien alertar si no esta relleno, si la edad en menor,
                // o si toodo esta correcto, nos dirá que la inscripcion se ha realizado con exito

                @Override
                public void onClick(View view) {

                  // Por tanto para ello tenemos que declarar dentro del onClick cada EditText del que queremos obtener su valor menos el del campo oculto

                    ///// OBTENEMOS VALOR DEL NOMBRE

                    // En el caso de querer obtener el valor de un EditText normal (no un TextInputEditText), como siempre hay que que declararlo y decirle que id tiene en la parte morada,

                    // Seria asi: EditText nombre = (EditText)findViewById(R.id.nombre);

                    // pero en el caso de ser un TextInputLayout, se pone en la parte morada el id del TextInputEditText que tiene dentro de el

                    EditText nombre = (EditText)findViewById(R.id.nombre_toast);

                    //Ahora si queremos obtener su valor en el Toast, tenemos que convertirlo a String

                    String valorNombre = nombre.getText().toString();

                    ///// OBTENEMOS VALOR DEL APELLIDO

                    EditText apellido = (EditText)findViewById(R.id.apellido_toast);

                    String valorApellido = apellido.getText().toString();

                    ///// OBTENEMOS VALOR DE LA EDAD

                    EditText edad = (EditText)findViewById(R.id.edad_toast);

                    //Ahora como para la edad queremos operar con un numero, tengo que convertirlo PRIMERO a String
                    // y despues a int para operar, y lo hago asi:

                    //Si la edad  NO(!) tiene ninguna letra ("") entonces es true (?), por tanto, sera igual a la edad cnvertido a String, y a su vez convertido a int.

                    int numeroIngresado = !edad.getText().toString().equals("") ? Integer.parseInt(edad.getText().toString()) : -1;

                    ///// OBTENEMOS VALOR DEL SEXO

                    RadioButton sexo=(RadioButton)findViewById(R.id.hombre);

                    // ahora que estan todos los EditText declarados, y convertidos a string y a int en el caso de la edad,
                    // mediante if compruebo que si se le da al boton Inscribirse sin haber introducido nada en ningun campo,
                    // entonces, que diga que se deben de rellenar todos los campos

                    //para decirle si esta vacio podemos poner: nombre.getText().toString().isEmpty  o  isEmpty(ValorNombre)
                    //es decir, en el primer caso, usando el nombre declarado del EditText (como en el caso para la edad que tiene que ser asi) y en el segundo caso,
                    //si hemos convertido a String, usamos el valor convertido a String como en el caso del resto de campos

                    // si la variable comprueba es false, es decir, no se ha tildado el check y por tanto no ha aparecido el campo oculto,  entonces,

                    if(comprueba==false) {

                        // aqui valido si todos los campos menos el campo oculto
                        if (isEmpty(valorNombre) && isEmpty(valorApellido) && edad.getText().toString().isEmpty() && !sexo.isChecked()) {
                            nombre.setError("Rellene este campo"); // Con este setError señalo el error a la derecha del campo con un simbolo rojo, y si nos ponemos encima nos muestra un mensaje
                            apellido.setError("Rellene este campo");
                            edad.setError("Rellene este campo");
                            //sexo.setError("Rellene este campo");

                            //Imprimo en un Toast
                            Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
                        }

                        // y ahora voy comprobando que campo se va rellenando y avisando sino se rellena, y se indica tanto en el Toast como en cada campo

                        else if (isEmpty(valorNombre)) {

                            nombre.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo nombre", Toast.LENGTH_LONG).show();
                        } else if (isEmpty(valorApellido)) {
                            apellido.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo apellido", Toast.LENGTH_LONG).show();
                        } else if (edad.getText().toString().isEmpty()) {
                            edad.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo edad", Toast.LENGTH_LONG).show();
                        }

                        // y aqui valido si tiene mas de 18 años, si no lo es marca error y no deja inscribirse
                        else if (numeroIngresado < 18) {
                            edad.setError("Edad menor de la permitida");
                            Toast.makeText(getApplicationContext(), "Debes ser mayor de edad para inscribirte!", Toast.LENGTH_SHORT).show();

                        } else if (!sexo.isChecked()) {
                            //sexo.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo sexo", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "La inscripción se ha realizado con éxito", Toast.LENGTH_LONG).show();

                        }


                    // si la variable comprueba es true, es decir, se ha tildado el check y a aparecido el campo oculto, ntoces
                    } else {

                        // Aqui comprueba todos los campos incluido el campo nombre_concurso
                        if (isEmpty(valorNombre) && isEmpty(valorApellido)&& edad.getText().toString().isEmpty() && !sexo.isChecked() && isEmpty(nombreConcursoString)) {
                            nombre.setError("Rellene este campo"); // Con este setError nos señala el error a la derecha delc ampo con un simbolo rojo, y si nos ponemos encima nos muestra un mensaje
                            apellido.setError("Rellene este campo");
                            edad.setError("Rellene este campo");
                            //sexo.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
                        }

                        // y ahora voy comprobando que campo se va rellenando y avisando sino se rellena tanto en el Toast com en cada campo

                        else if (isEmpty(valorNombre)) {
                            nombre.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo nombreeeeeee", Toast.LENGTH_LONG).show();
                        } else if (isEmpty(valorApellido)) {
                            apellido.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo apellido", Toast.LENGTH_LONG).show();
                        } else if (edad.getText().toString().isEmpty()) {
                            edad.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo edad", Toast.LENGTH_LONG).show();
                        }

                        // y aqui valido si tiene mas de 18 años, si no lo es marca error y no deja inscribirse
                        else if (numeroIngresado < 18) {
                            edad.setError("Edad menor de la permitida");
                            Toast.makeText(getApplicationContext(), "Debes ser mayor de edad para inscribirte!", Toast.LENGTH_SHORT).show();

                        } else if (!sexo.isChecked()) {
                            // sexo.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo sexo", Toast.LENGTH_LONG).show();

                        // aqui comprueba el campo oculto y si no se rellena salta un error tanto en el campo como en el Toast

                        } else if (isEmpty(nombre_concurso.getText().toString())) {
                            nombre_concurso.setError("Rellene este campo");
                            Toast.makeText(getApplicationContext(), "Debe rellenar el campo Otros concursos", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "La inscripción se ha realizado con éxito", Toast.LENGTH_LONG).show();

                        }
                    }

                }

            });

    }

}





