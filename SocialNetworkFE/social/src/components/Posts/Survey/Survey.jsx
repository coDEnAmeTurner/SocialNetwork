import { useContext, useEffect } from "react";
import { QuestionsContext } from "../../../configs/Contexts";
import SurveyQuestion from "../SurveyQuestions/SurveyQuestion";
import { SurveyMode } from "../../../utils/accessMode";

let nextQuestionIndex = 0;

const Survey = ({ surveyMode = SurveyMode["forCreation"] }) => {
  const [questions, questionsDispatch] = useContext(QuestionsContext);

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

  const updateQuestionsVotes = () => {
    //fetch real update api
  };

  useEffect(() => {
    if (surveyMode === SurveyMode.forEdit)
      nextQuestionIndex =
        questions && questions.length > 0
          ? (Math.max(...questions.map(({id})=>id)) + 1)
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
            />
          );
        })
      ) : (
        <></>
      )}

      {(surveyMode === SurveyMode["forCreation"] || surveyMode === SurveyMode['forEdit']) ? (
        <div className="makepost-add-choice" onClick={addQuestion}>
          Add Question
        </div>
      ) : (
        <></>
      )}

      {surveyMode === SurveyMode["forDisplay"] ? (
        <div className="makepost-add-choice" onClick={updateQuestionsVotes}>
          Vote
        </div>
      ) : (
        <></>
      )}
    </>
  );
};

export default Survey;
