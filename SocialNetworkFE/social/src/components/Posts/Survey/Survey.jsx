import { useContext, useEffect, useState } from "react";
import { QuestionsContext } from "../../../configs/Contexts";
import SurveyQuestion from "../SurveyQuestions/SurveyQuestion";
import { SurveyMode } from "../../../utils/accessMode";
import { authApi, endpoints } from "../../../configs/APIs";

let nextQuestionIndex = 0;

const Survey = ({ surveyMode = SurveyMode["forCreation"] }) => {
  const [questions, questionsDispatch] = useContext(QuestionsContext);
  const [voting, setVoting] = useState(false);

  const addQuestion = (event) => {
    let edit = questions != null ? structuredClone(questions) : [];
    edit.push({
      id: nextQuestionIndex++,
      content: null,
      choices: null,
    });
    questionsDispatch({
      type: "set",
      payload: edit,
    });
  };

  const updateQuestionsVotes = async () => {
    setVoting(true);

    //fetch real update api
    const questionForm = new FormData();
    questions.forEach((q) => {
      questionForm.append(`${q.id}`, `${q.vote}`);
    });
    const cUpRes = await authApi().post(endpoints["vote"], questionForm, {
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (cUpRes.status === 201) {
      setVoting(false);
    }
  };

  useEffect(() => {
    if (surveyMode === SurveyMode.forEdit)
      nextQuestionIndex =
        questions && questions.length > 0
          ? Math.max(...questions.map(({ id }) => id)) + 1
          : nextQuestionIndex;
  }, []);

  return (
    <>
      <div>
        <label className="Survey"> Survey Questions: </label>
      </div>

      {questions && questions.length > 0 ? (
        questions.map((question) => {
          return (
            <SurveyQuestion
              surveyMode={surveyMode}
              key={question.id}
              content={question.content}
              id={question.id}
              choices={question.choices}
              vote = {question.vote}
            />
          );
        })
      ) : (
        <></>
      )}

      {surveyMode === SurveyMode["forCreation"] ||
      surveyMode === SurveyMode["forEdit"] ? (
        <div className="makepost-add-choice" onClick={addQuestion}>
          Add Question
        </div>
      ) : (
        <></>
      )}

      {surveyMode === SurveyMode["forDisplay"] ? (
        !voting ? (
          <div className="makepost-add-choice" onClick={updateQuestionsVotes}>
            Confirm Vote
          </div>
        ) : (
          <button class="makepost-add-choice" type="button" disabled>
            <span
              class="spinner-border spinner-border-sm"
              role="status"
              aria-hidden="true"
            ></span>
          </button>
        )
      ) : (
        <></>
      )}
    </>
  );
};

export default Survey;
