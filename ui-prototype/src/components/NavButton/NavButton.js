import React from "react";
import { Link } from "react-router-dom";

const NavButton = ({ children, disabled, to }) => (
    <Link to={to} disabled={disabled}>
        <button type="button" style={{ width: "80px", marginRight: 5 }} disabled={disabled}>
            {children}
        </button>
    </Link>
);

export default NavButton;
