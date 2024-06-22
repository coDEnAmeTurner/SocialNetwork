import './SurveyChoice.css';
import Form from 'react-bootstrap/Form';
import { AiFillCloseCircle } from "react-icons/ai";
import { IconContext } from "react-icons";
import { useContext, useState } from 'react';
import { QuestionsContext } from '../../../configs/Contexts';
import { SurveyMode } from '../../../utils/accessMode';

const SurveyChoice = ({ surveyMode, content, id, qId }) => {
    const [questions, questionsDispatch] = useContext(QuestionsContext);
    const [hidden, setHidden] = useState(false);

    const removeChoice = (e) => {
        let editQuestions = structuredClone(questions);
        let currentQuestion = editQuestions.find((q) => q.id === qId);
        let ci = currentQuestion.choices.findIndex((choice) => { return choice.id === id });
        let editedChoices = structuredClone(currentQuestion.choices);
        editedChoices[ci].deleted = true;
        currentQuestion.choices = editedChoices;
        questionsDispatch({
            type: "set",
            payload: editQuestions
        })

        setHidden(true);
    }

    const handleContentChange = (e) => {
        let editQuestions = structuredClone(questions);
        let currentQuestion = editQuestions.find((q) => q.id === qId);
        let currentDataIndex = currentQuestion.choices.findIndex(obj => obj.id === id);
        currentQuestion.choices[currentDataIndex].content = e.target.value;
        questionsDispatch({
            type: "set",
            payload: editQuestions
        })
    }
    
    const setChoiceVote = (e) => {
        let editQuestions = structuredClone(questions);
        let currentQuestion = editQuestions.find((q) => q.id === qId);
        currentQuestion.vote = id;
        questionsDispatch({
            type: "set",
            payload: editQuestions
        })
    }

    return (
        hidden?<></>:
        <IconContext.Provider value={{ color: "red", size: "3em", className: "global-class-name" }}>
            <div key={`default-radio`} className="radio-set" style={{
                marginTop: "0.7rem"
            }}>
                <Form.Check // prettier-ignore
                    type="radio"
                    name="group"
                    id={`default-radio`}
                    onClick={setChoiceVote}
                />
                <input
                    required
                    id={`choice-content-${id}`}
                    name={`choiceContent-${id}`}
                    type="text"
                    className={`choice-content-${id}`}
                    onChange={(event) => {
                        handleContentChange(event);
                    }}
                    value={content}
                    style={{
                        height: "40px",
                        backgroundColor: "#535353",
                        color: 'white',
                        border: 'none',
                        fontSize: '1.15rem',
                        fontFamily: "Noto Sans, sans-serif",
                        marginTop: '0',
                        marginBottom: '0',
                        marginLeft: '20px'
                    }}
                />

                {
                    ((surveyMode === SurveyMode.forCreation || surveyMode === SurveyMode.forEdit)) ? 
                    <div className="makepost-remove-choice" onClick={removeChoice}>
                        <AiFillCloseCircle />
                    </div>:
                    <></>
                }

            </div>
        </IconContext.Provider >
    )
}

export default SurveyChoice;
