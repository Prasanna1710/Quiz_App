package psv.app.quizapp

object QuestionData {

    //variables to store values to send between activities
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANS: String = "total_ans"

    fun getQuestion(): ArrayList<QuestionDataClass>
    {
        val questionList = ArrayList<QuestionDataClass>()

        val q1 = QuestionDataClass(1, "What country does this flag belong to",
        R.drawable.india_flag, "America", "Iran", "India", "Netherlands",
        3)

        val q2 = QuestionDataClass(2, "Which animal is this",
            R.drawable.lion,"Tiger", "Elephant", "Monkey", "Lion",
            4)

        val q3 = QuestionDataClass(3, "Who is this player",
            R.drawable.iniesta,"Iniesta", "Xavi", "Zidane", "Modric",
            1)

        val q4 = QuestionDataClass(4, "Who's Jersey is This",
            R.drawable.man_united,"Manchester City", "Liverpool", "Manchester United",
            "Barcelona",
            3)

        val q5 = QuestionDataClass(5, "Who is this",
            R.drawable.churchil,"Churchill", "Roosevelt", "Stalin", "Mountbatten",
            1)

        val q6 = QuestionDataClass(6, "What is the name of this Mountain",
            R.drawable.everest,"K2", "Alps", "Lhotse", "Everest",
            4)

        val q7 = QuestionDataClass(7, "Who is this",
            R.drawable.tesla,"Tesla", "Edison", "Plank", "Oppenheimer",
            1)

        val q8 = QuestionDataClass(8, "Who's logo is this",
            R.drawable.google,"Microsoft", "Google", "Amazon", "Alphabet",
            2)

        val q9 = QuestionDataClass(9, "Which character is this",
            R.drawable.jon_snow,"Bran", "Jon Snow", "Spartacus", "Hound",
            2)

        val q10 = QuestionDataClass(10, "Which planet is this",
            R.drawable.jupiter,"Saturn", "Mars", "Jupiter", "Mercury",
            3)

        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)
        questionList.add(q5)
        questionList.add(q6)
        questionList.add(q7)
        questionList.add(q8)
        questionList.add(q9)
        questionList.add(q10)

        return questionList

    }
}