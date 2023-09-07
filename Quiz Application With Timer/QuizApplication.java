import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class QuizApplication
{
    private String question;
    String[] options;
    int correctOption;
    public QuizApplication(String question, String[] options, int correctOption)
    {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
    public boolean isCorrect(int userAnswer)
    {
        return userAnswer == correctOption;
    }
    public void displayQuestion()
    {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

class Quiz
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<QuizApplication> geographyQuestions = new ArrayList<>();
        List<QuizApplication> historyQuestions = new ArrayList<>();

        geographyQuestions.add(new QuizApplication("Which is the tallest mountain range in the world?",
                new String[]{"Himalayas", "Andes", "Rocky Mountains", "Atlas Mountains"}, 1));
        geographyQuestions.add(new QuizApplication("Which is the most populated country in Asia?",
                new String[]{"India", "China", "Malaysia", "Pakistan"}, 2));
        geographyQuestions.add(new QuizApplication("Which one is the world's largest continent?",
                new String[]{"Africa", "Asia", "North America", "South America"}, 2));

        historyQuestions.add(new QuizApplication("Who was the first President of the United States?",
                new String[]{"George Washington", "Thomas Jefferson", "John Adams", "Benjamin Franklin"}, 1));
        historyQuestions.add(new QuizApplication("In which year did World War II end?",
                new String[]{"1939", "1945", "1950", "1940"}, 2));
        historyQuestions.add(new QuizApplication("Who is the first Muslim woman to receive a Nobel Prize?",
                new String[]{"Malala Yousafzai", "Tawakel Kamran", "Shireen Fatima", "Shirin Ebadi"}, 4));

        int score = 0;
        int totalQuestions = geographyQuestions.size() + historyQuestions.size();
        int subjectTimeLimitInSeconds = 10;

        while (true)
        {
            System.out.println("Select a subject:");
            System.out.println("1. Geography");
            System.out.println("2. History");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int subjectChoice = scanner.nextInt();
            scanner.nextLine();

            if (subjectChoice == 3)
            {
                break;
            }
            else if (subjectChoice < 1 || subjectChoice > 2)
            {
                System.out.println("Invalid choice. Please enter 1 for Geography, 2 for History, or 3 to exit.");
                continue;
            }

            List<QuizApplication> selectedQuestions = (subjectChoice == 1) ? geographyQuestions : historyQuestions;
            Random random = new Random();

            for (int i = 0; i < selectedQuestions.size(); i++)
            {
                QuizApplication currentQuestion = selectedQuestions.get(i);
                System.out.println("Question " + (i + 1) + ":");
                currentQuestion.displayQuestion();
                int userAnswer;
                final boolean[] answered = {false};

                Timer timer = new Timer();
                TimerTask task = new TimerTask()
                {
                    int remainingTime = subjectTimeLimitInSeconds;

                    @Override
                    public void run()
                    {
                        if (remainingTime == subjectTimeLimitInSeconds)
                        {
                            System.out.println("You have " + remainingTime + " seconds for this question.");
                        }
                        if (remainingTime == 0)
                        {
                            System.out.println("\nTime's up for this question!\n");
                            timer.cancel();
                            answered[0] = true;
                        }
                        else
                        {
                            System.out.print("Time Remaining: " + remainingTime + " seconds\r");
                        }
                        remainingTime--;
                    }
                };

                timer.scheduleAtFixedRate(task, 0, 1000); // Start the timer

                do
                {
                    System.out.print("Enter your answer (1-" + currentQuestion.options.length + "): ");
                    userAnswer = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (userAnswer < 1 || userAnswer > currentQuestion.options.length)
                    {
                        System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
                while (!answered[0] && userAnswer < 1 || userAnswer > currentQuestion.options.length);
                timer.cancel();

                if (answered[0]) {
                    continue;
                }

                if (currentQuestion.isCorrect(userAnswer))
                {
                    System.out.println("Correct!\n");
                    score++;
                }
                else {
                    System.out.println("Incorrect. The correct answer is: " + currentQuestion.options[currentQuestion.correctOption - 1] + "\n");
                }
            }
            System.out.println("Subject quiz completed!");
        }

        System.out.println("Quiz completed!");
        System.out.println("Your Total Score: " + score + " out of " + totalQuestions);
        scanner.close();
    }
}
