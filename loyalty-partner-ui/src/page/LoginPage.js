import React, { useRef } from "react";
import { Link } from "react-router-dom";
import axios from "../api/axios";
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import '../css/login.css';
import logo from '../assests/logo-login.png'
import logo1 from '../assests/logo.jpg'

const LoginPage = () => {

    const userName = useRef("");  
    const password = useRef("");

    const login = async (payload) => {
        try {
          const apiResponse = await axios.post(
            axios.defaults.baseURL + `/api/user/loginWithPartner`,
            payload
          );
      
          if (apiResponse?.status === 200) {
            localStorage.setItem(
              "userId",
              JSON.stringify(apiResponse?.data?.data?.user_id)
            );
            window.location.href = "/label";
          } else {
            toast.error("Có lỗi xảy ra! Vui lòng thử lại!");
          }
        } catch (error) {
          if (error?.request?.status === 401) {
            toast.error("Sai tên tài khoản hoặc mật khẩu! Vui lòng thử lại!");
          } else {
            toast.error("Có lỗi xảy ra! Vui lòng thử lại!");
          }
        }
      };

    const loginSubmit = async (e) => {
        e.preventDefault()
      let payload = {
        username: userName.current.value,
        password: password.current.value
      }
      await login(payload);
     };

    return (
        <>
            <div className="login-page">
                <div className="login-logo">
                    <img
                        src={logo}
                        alt="Logo"
                        href="/dashboard"
                        style={{
                            width: "100%",
                            height: "100%",
                        }}
                    />
                </div>
                <div className="login-form">
                    <div className="bg">
                        <div className="form">
                            <div className="form-toggle"></div>
                            <div className="form-panel one">
                                <div className="form-header-logo">
                                    <img
                                        src={logo1}
                                        alt="Logo"
                                        href="/dashboard"
                                        style={{ width: "60px", height: "60px"}}
                                    />
                                </div>
                                <div className="form-header">
                                    <h1>Đăng nhập</h1>
                                </div>
                                <div className="form-content">
                                    <form
                                    onSubmit={loginSubmit}
                                    >
                                        <div className="form-group">
                                            <label htmlFor="username">Tên đăng nhập</label>
                                            <input type="text" id="username"
                                                ref={userName}
                                                required />
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="password">Mật Khẩu</label>
                                            <input
                                                type="password"
                                                id="password"
                                                ref={password}
                                                required
                                            />
                                        </div>
                                        <div className="form-group">
                                            <button variant="primary" type="submit" className="btn-submit">
                                                Đăng nhập
                                            </button>
                                        </div>
                                        <div className="form-group">
                                            <Link className="link-item-login">
                                                Yêu cầu đăng ký tài khoản
                                            </Link>
                                            <Link className="link-item-login" to="/forgot-pass">
                                                Quên mật khẩu?
                                            </Link>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default LoginPage;