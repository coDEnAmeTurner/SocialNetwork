import "./inputfield.css";
const InputField = (props) => {
  const {
    id,
    name,
    value,
    placeholder,
    inputType,
    type,
    setData,
    label,
    classStyle,
  } = props;
  return (
    <>
      <label> {label} </label>
      {inputType === "textarea" ? (
        <textarea
          type="text"
          value={value}
          className={classStyle}
          placeholder={placeholder}
          onChange={(e) => setData(e.target.value)}
          
        />
      ) : inputType === "file" ? (
        <input
          value={value}
          id={id}
          name={name}
          type={type}
          className={classStyle}
          placeholder={placeholder}
          onChange={(e) => setData(e.target.value)}
        />
      ) : (
        <>
          <input
            required
            value={value}
            type={type}
            className={classStyle}
            placeholder={placeholder}
            onChange={(e) => setData(e.target.value)}
            style={
              {
                fontSize: '1rem',
                marginLeft: '1rem',
                backgroundColor: '#3b3b3b',
                color: 'white',
                width: '100%',
                padding: '0.75rem 0.5rem',
                borderRadius: '12px'
              }
            }
          />
        </>
      )}
    </>
  );
};

export default InputField;