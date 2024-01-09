import axios from "./axios";
import { toast } from "react-toastify";

const axiosApiInstance = axios.create({});

axiosApiInstance.interceptors.request.use(
    (config) => {
      let userId = JSON.parse(localStorage.getItem("userId"));
      console.log(userId);
      if (userId === null) {
        localStorage.clear();
        toast.info("Vui lòng đăng nhập để tiếp tục!", { autoClose: 50000 });
        window.location.href = "/login";
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

export default axiosApiInstance;