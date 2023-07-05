import React from "react";

// Animated Css
// https://animate.style/

const Popup = (props) => {
  return (
    <div className="popup-box">
      <div className="box animate__animated animate__fadeInDown animate__faster">{props.content}</div>
    </div>
  );
};

export default Popup;
