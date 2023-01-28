package psv.app.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent

class ResultActivity : AppCompatActivity() {

    private var score: TextView? = null
    private var name: TextView? = null
    private var finish: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /* here we will be sending data from one activity to other like username and total score
        The constant/object file in our project store all constant variables
        it is also good to store the variables which we will use to transfer data inside constant
        file
         */

        //initialize the variables to views in activity
        name = findViewById(R.id.name)
        score = findViewById(R.id.score)
        finish = findViewById(R.id.finish_button)

        //set variables to the values received from intent
        val displayName = intent.getStringExtra(QuestionData.USER_NAME)
        val totalQuestion = intent.getStringExtra(QuestionData.TOTAL_QUESTIONS)
        val correctAns = intent.getStringExtra(QuestionData.CORRECT_ANS)

        name?.text = displayName
        score?.text = "Your Score is ${correctAns}/${totalQuestion}"

        //when finish button pressed finish the activity
        finish?.setOnClickListener {
            finish()
        }

    }
}