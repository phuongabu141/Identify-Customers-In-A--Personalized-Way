import React, { useState } from "react";
import { Link, NavLink } from "react-router-dom";
import '../css/user-header.css'
import logo from '../assests/logo.jpg';

const UserHeader = () => {

    const handleClick = () => setClick(!click);
    const [click, setClick] = useState(false);

    const closeMobileMenu = () => setClick(false);

    return (
        <>
            <nav style={{ background: "rgb(255 255 0 / 8%)" }} className="navbar header nav-light navbar-expand-lg navbar-light shadow fixed-top">
                <Link to="/label" className="navbar-logo" onClick={closeMobileMenu}>
                    <img
                        src={logo}
                        alt="Logo"
                        href="/label"
                        style={{ width: "45px", height: "45px" }}
                    />
                    <span style={{ marginLeft: "5px" }}>LOYALTY</span>
                </Link>
                <div className='menu-icon' onClick={handleClick}>
                    <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
                </div>
                <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                    <li className="nav-item">
                        <NavLink to="/label" activeClassName="active" className="nav-links" onClick={closeMobileMenu}>
                            Label
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/customer-label" activeClassName="active" className="nav-links" onClick={closeMobileMenu}>
                            Khách hàng
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/partner-request" activeClassName="active" className="nav-links" onClick={closeMobileMenu}>
                            Đề xuất
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/notification" activeClassName="active" className="nav-links" onClick={closeMobileMenu}>
                            Thông báo
                        </NavLink>
                    </li>
                </ul>
            </nav>
        </>
    );
};

export default UserHeader;