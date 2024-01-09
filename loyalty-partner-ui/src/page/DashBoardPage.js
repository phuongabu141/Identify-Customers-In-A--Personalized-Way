import React, { useEffect, useState } from "react";
import userLayout from "../layout/userLayout";
import axios from "../api/axios";
import axiosApiInstance from "../api/interceptor";

const DashBoardPage = () => {

    const [listLabel, setListLabel] = useState([]);

    async function getLabelMapGroup() {
        const result = await axiosApiInstance.get(axios.defaults.baseURL + `/api/label/LabelMapGroup`);
        setListLabel(result?.data?.data);
    }

    useEffect(() => {
        getLabelMapGroup();
    }, []);

    console.log(listLabel);

    return (
        <>
            <div className="container" style={{ minWidth: "90%" }}>
                <h5 style={{textAlign: "center"}}>Danh sách label đã được gán cho khách hàng</h5>
            </div>
        </>
    );
};

export default userLayout(DashBoardPage);