import { useContext, useEffect, useState } from "react";
import SurveyChoice from "../SurveyChoice/SurveyChoice";
import { QuestionsContext } from "../../../configs/Contexts";
import "./SurveyQuestion.css";
import { AiFillCloseCircle } from "react-icons/ai";
import Form from "react-bootstrap/Form";
import { IconContext } from "react-icons";
import { SurveyMode } from "../../../utils/accessMode";

let nextChoiceIndex = 0;

const SurveyQuestion = ({ surveyMode, id, content, choices }) => {
  const [questions, questionsDispatch] = useContext(QuestionsContext);
  const [hidden, setHidden] = useState(false);

  const addChoice = () => {
    let edit = questions != null ? structuredClone(questions) : [];
    //find the current question
    let currentQuestion = edit.find((q) => q.id === id);
    //add choice obj to current question
    let editChoices = currentQuestion.choices
      ? structuredClone(currentQuestion.choices)
      : [];
    editChoices.push({
      id: nextChoiceIndex++,
      content: null,
    });
    currentQuestion.choices = editChoices;
    questionsDispatch({
      type: "set",
      payload: edit,
    });
  };

  const removeQuestion = (e) => {
    let editedQuestions = structuredClone(questions);
    let i = editedQuestions.findIndex((q) => q.id === id);
    editedQuestions[i].deleted = true;
    console.log(editedQuestions[i]);
    questionsDispatch({
      type: "set",
      payload: editedQuestions,
    });
    
    setHidden(true);
  };

  const handleQuestionContentChange = (e) => {
    let currentDataIndex = questions.findIndex((obj) => obj.id === id);
    let edited = structuredClone(questions);
    edited[currentDataIndex].content = e.target.value;
    questionsDispatch({
      type: "set",
      payload: edited,
    });
  };

  useEffect(() => {
    if (surveyMode === SurveyMode.forEdit)
      nextChoiceIndex =
        choices && choices.length > 0
          ? Math.max(
              ...questions.map((question) =>
                Math.max(...question.choices.map(({ id }) => id))
              )
            ) + 1
          : nextChoiceIndex;

    console.log(nextChoiceIndex);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    hidden?<></>:
    <IconContext.Provider
      value={{ color: "red", size: "3em", className: "global-class-name" }}
    >
      <>
        <div
          style={{
            display: "flex",
          }}
        >
          <textarea
            required
            id={`question-content-${id}`}
            name={`questionContent-${id}`}
            type="text"
            className={`question-content-${id}`}
            placeholder="Enter the question"
            onChange={
              surveyMode === SurveyMode.forCreation || SurveyMode.forEdit
                ? handleQuestionContentChange
                : null
            }
            value={content}
            style={{
              height: "50px",
              backgroundColor: "#535353",
              color: "white",
              border: "none",
              fontSize: "1.15rem",
              fontFamily: "Noto Sans, sans-serif",
              padding: "0.25rem 0.5rem 4rem 0.5rem",
              marginTop: "1rem",
              marginBottom: "1rem",
              width: "100%",
            }}
          />

          {surveyMode === SurveyMode.forCreation ||
          surveyMode === SurveyMode.forEdit ? (
            <div className="makepost-remove-choice" onClick={removeQuestion}>
              <AiFillCloseCircle />
            </div>
          ) : (
            <></>
          )}
        </div>

        {choices && choices.length > 0 ? (
          <Form>
            {choices.map((choiceData) => (
              <SurveyChoice
                surveyMode={surveyMode}
                key={choiceData.id}
                content={choiceData.content}
                id={choiceData.id}
                qId={id}
              />
            ))}
          </Form>
        ) : (
          <></>
        )}

        {surveyMode === SurveyMode.forCreation ||
        surveyMode === SurveyMode.forEdit ? (
          <div className="makepost-add-choice" onClick={addChoice}>
            Add Choice
          </div>
        ) : (
          <></>
        )}
      </>
    </IconContext.Provider>
  );
};

export default SurveyQuestion;
