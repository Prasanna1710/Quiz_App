  package psv.app.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

  class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start: Button = findViewById(R.id.Start)
        val nameInput: EditText = findViewById(R.id.nameField)

        start.setOnClickListener {
            if (nameInput.text.isNotEmpty())
            {
                var intent = Intent(this, QuestionsActivity::class.java)
                /*
                Intents can also be used to send data between activities
                we use the .putExtra() method for that
                data is sent between 2 activities which are connected by intent
                 */
                intent.putExtra(QuestionData.USER_NAME, nameInput.text.toString())
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this,"Please enter name", Toast.LENGTH_LONG).show()
            }
        }

    }
}