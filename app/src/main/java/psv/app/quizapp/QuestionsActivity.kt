package psv.app.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


/*
View.OnClickListener is an interface in Android
that is used to detect clicks on a View component.
It has a single method, onClick(View v),
that is called when the view is clicked.
The following declaration allows the class QuestionsActivity to use
View.OnClickListener on the views inside that activity
 */

class QuestionsActivity() : AppCompatActivity(), View.OnClickListener{

    /*
    Declaring variable for each view inside Activity
     */
    private var questionUI: TextView? = null
    private var image: ImageView? = null

    private var option1: TextView? = null
    private var option2: TextView? = null
    private var option3: TextView? = null
    private var option4: TextView? = null

    private var progressText: TextView? = null
    private var progressBar: ProgressBar? = null

    private var submit: Button? = null

    //Creating a arraylist of type QuestionDataClass
    private var questions: ArrayList<QuestionDataClass>? = null

    //a global variable for traversing through "questions" array
    private var currentPosition = 1

    //It shows the Id of selected option (1 or 2 or 3 or 4)
    private var selectedOption: Int = 0

    //create variables to retrieve and store intent values
    var userName: String? = null

    //Shows the Id of correct option of question
    var correctAns: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        /*
        We used intent.putExtra in mainActivity to get send intent data to this activity
        now we can use getStringExtra (because we sent string)
        in this activity to retrieve that data
         */
        userName = intent.getStringExtra(QuestionData.USER_NAME)

        setUI()
        setQuestion()

        //Below line of code listens for click on 4 options and submit button
        option1?.setOnClickListener(this)
        option2?.setOnClickListener(this)
        option4?.setOnClickListener(this)
        option3?.setOnClickListener(this)
        submit?.setOnClickListener(this)


        //Log.i("Number of questions is", "${questions?.size}")
    }

    private fun setUI() {
        // Initialize the variables to their respective views
        questionUI = findViewById(R.id.question)
        image = findViewById(R.id.question_image)

        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_text)

        option1 = findViewById(R.id.Option1)
        option2 = findViewById(R.id.Option2)
        option3 = findViewById(R.id.Option3)
        option4 = findViewById(R.id.Option4)

        submit = findViewById(R.id.submit)

        //loads the question array from QuestionData object file
        // into questions variable
        questions = QuestionData.getQuestion()
    }

    //This function is called at the start to load 1st question by default
    private fun setQuestion()
    {
        //the set default option sets initial background for each option
        setDefaultOption()

        /*
        Assigns the variable q to Question object from an array
        depending on current position
         */
        var q = questions!![currentPosition-1]

        //below lines load the current question on UI
        questionUI?.text = q.Question
        image?.setImageResource(q.Image)
        progressBar?.progress = currentPosition
        progressText?.text = "$currentPosition/${progressBar?.max}"
        option1?.text = q.Option1
        option2?.text = q.Option2
        option3?.text = q.Option3
        option4?.text = q.Option4
    }

   //As we have added OnClick interface to class we override the interface's method
   //to do our own function
    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.Option1 ->
                //.let{}, lets the option to perform setSelectedOption function on itself
                option1?.let{
                    setSelectedOption(it, 1) }
            R.id.Option2 ->
                option2?.let{
                    setSelectedOption(it, 2) }
            R.id.Option3 ->
                option3?.let{
                    setSelectedOption(it, 3) }
            R.id.Option4 ->
                option4?.let{
                    setSelectedOption(it, 4) }
            R.id.submit -> {
                if(selectedOption == 0)
                {
                    currentPosition += 1
                    //as long as currentPosition value is inside arraySize
                    if (currentPosition <= questions!!.size)
                    {
                        //currentPosition += 1
                        setQuestion()
                        submit?.text = "Submit"
                        //setDefaultOption()
                    }
                    //if not move to next screen
                    else{
                        //This is the place to switch activities
                        val intent = Intent(this, ResultActivity::class.java)

                        //pass userName, TOTAL_QUESTIONS, Number of correct ans to next activity
                        intent.putExtra(QuestionData.USER_NAME, userName)
                        intent.putExtra(QuestionData.TOTAL_QUESTIONS, (questions?.size).toString())
                        intent.putExtra(QuestionData.CORRECT_ANS, correctAns.toString())

                        //Start next activity
                        startActivity(intent)

                        //finish current activity
                        finish()

                        //Toast.makeText(this, "$userName", Toast.LENGTH_LONG).show()
                    }
                }
                //when option is already selected and we press submit
                else{
                    val currentQuestion = questions!![currentPosition -1]
                    if(selectedOption == currentQuestion.CorrectOption)
                    {
                        answerView(selectedOption, R.drawable.correct_ans_backgroud)
                        correctAns += 1
                    }
                    else{
                        answerView(currentQuestion.CorrectOption, R.drawable.correct_ans_backgroud)
                        answerView(selectedOption, R.drawable.wrong_ans_background)
                    }

                    submit?.text = "Next"
                    selectedOption = 0
                }
            }
        }
    }

    //This function highlights the selected option before moving on to ans screen
    private fun setSelectedOption(tv: TextView, selectedOptionNum: Int)
    {
        setDefaultOption()

        //Assigns the val passed to selectedOption global variable
        selectedOption = selectedOptionNum

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border)

    }


    private fun setDefaultOption()
    {
        val options = ArrayList<TextView>()

        //Adds all options to an TextView Array
        option1?.let {
                options.add(0,it)
            }
        option2?.let {
            options.add(1,it)
        }
        option3?.let {
            options.add(2,it)
        }
        option4?.let {
            options.add(3,it)
        }

        //sets the text color, typeface and background to every option
        for(option in options)
        {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.rectangle_border_bg)
        }
    }


    /*
    Depending on answer/optionId and backgrounds id from drawable folder passed
    This function sets the passed background to option
     */
    private fun answerView(answer: Int, drawable: Int)
    {
        /*
        ContextCompat.getDrawable() is a method in the Android Support Library's ContextCompat class
        that allows you to retrieve a Drawable from a given resource ID,

        The context parameter is used to determine the current theme and the device's API level,
        which are both used to retrieve the correct Drawable from the given resource ID.

        For example, the context can be an instance of the Activity class,
        which represents a single screen in an app
         */
        when(answer)
        {
            /*
            when option id is one of the following
            the options background is set to drawable passed
             */
            1 -> option1?.background =
                ContextCompat.getDrawable(this, drawable)
            2 -> option2?.background =
                ContextCompat.getDrawable(this, drawable)
            3 -> option3?.background =
                ContextCompat.getDrawable(this, drawable)
            4 -> option4?.background =
                ContextCompat.getDrawable(this, drawable)
        }
    }
}