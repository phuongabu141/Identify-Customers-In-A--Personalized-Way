import React, { useEffect, useState } from "react";
import userLayout from "../layout/userLayout";
import ReactLoading from 'react-loading';
import axios from "../api/axios";
import { FaPen, FaTrashAlt } from "react-icons/fa";
import { Button, Form, Modal } from "react-bootstrap";
import { toast } from "react-toastify";

const PartnerRequest = () => {

    const [load, setLoad] = useState(false);
    const [listPartnerRequest, setListPartnerRequest] = useState([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const [newPartnerRequest, setNewPartnerRequest] = useState({
        label_name: "",
        description: "",
    });

    async function getListPartnerRequest() {
        const result = await axios.get(axios.defaults.baseURL + `/api/PartnerRequest/all`);
        setLoad(true);
        setListPartnerRequest(result?.data?.data);
    };


    const handleAddPartnerRequest = async () => {
        try {
            const result = await axios.post(axios.defaults.baseURL + `/api/PartnerRequest/add`, newPartnerRequest);
            if (result.status === 201) {
                toast.success(result?.data?.message);
            }
        }
        catch (error) {
            toast.error("Có lỗi. Vui lòng thử lại!");
        }
    };

    const handleShowAdd = (e) => {
        setShow(true);
    }

    useEffect(() => {
        getListPartnerRequest();
    }, [load]);

    return (
        <>
            {load ? (
                <div className="d-flex justify-content-center">
                    <div className="table-container" style={{ width: "90%" }}>
                        <div className="justify-content-center">
                            <div className="row">
                                <div className="col text-start">
                                    <h4 className="pb-2 mb-0">Danh sách yêu cầu</h4>
                                </div>
                                <div className="col text-end">
                                    <button className="btn btn-default low-height-btn bg-success" style={{ height: "100%" }} onClick={handleShowAdd}>
                                        Thêm đề xuất
                                    </button>
                                </div>
                            </div>
                            <div className="d-flex text-muted overflow-auto center">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" className="col-1 col-name">Mã yêu cầu</th>
                                            <th scope="col" className="col-1 col-name">Tên label</th>
                                            <th scope="col" className="col-2 col-name">Mô tả</th>
                                            <th scope="col" className="col-1 col-name">Thời gian tạo</th>
                                            <th scope="col" className="col-1 col-name">Trạng thái</th>
                                            <th scope="col" className="col-1 col-name">Tác vụ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {listPartnerRequest.map((request) => (
                                            <tr className="tr-hover" key={request.request_id}>
                                                <td>{request.request_id}</td>
                                                <td>{request.label_name}</td>
                                                <td>{request.description}</td>
                                                <td>{request.created_time}</td>
                                                <td>{request.status}</td>
                                                <td style={{ whiteSpace: 'nowrap' }}>
                                                    <button type="button"
                                                        className="btn btn-outline-warning btn-light btn-sm mx-sm-1 px-lg-2 w-32"
                                                        title="Chỉnh sửa"
                                                    // id={item.id}
                                                    >
                                                        <FaPen /></button>
                                                    <button type="button"
                                                        // id={item.id}
                                                        className="btn btn-outline-danger btn-light btn-sm mx-sm-1 px-lg-2 w-32"
                                                        title="Xóa"><FaTrashAlt />
                                                    </button>
                                                </td>
                                            </tr>))}
                                    </tbody>
                                </table>
                            </div>
                            <div className="row">
                                {/* <div className="col">
                                    <Pagination
                                        onChange={handlePageChange}
                                        current={currentPage}
                                        pageSize={pageSize}
                                        total={listCustomer.length}
                                    />
                                </div> */}
                            </div>
                        </div>
                    </div>
                </div>
            )
                :
                (
                    <div>
                        <ReactLoading
                            type={"cylon"}
                            color="#fffff"
                            height={"11px"}
                            width={"9%"}
                        />
                    </div>
                )
            }

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Thêm đề xuất</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form
                    // onSubmit={handleSubmit}
                    >
                        <Form.Group className="mb-2">
                            <Form.Control type="text" placeholder="Tên label" name="name" required onChange={(e) => setNewPartnerRequest({ ...newPartnerRequest, label_name: e.target.value })}/>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Control type="text" placeholder="Mô tả tiêu chí" name="name" required onChange={(e) => setNewPartnerRequest({ ...newPartnerRequest, description: e.target.value })}/>
                        </Form.Group>
                        <Button variant="success" type="submit" onClick={handleAddPartnerRequest}>
                            Gửi đề xuất
                        </Button>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Đóng
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default userLayout(PartnerRequest);