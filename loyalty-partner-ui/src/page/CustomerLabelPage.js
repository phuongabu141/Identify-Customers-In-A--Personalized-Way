import React, { useEffect, useState } from "react";
import userLayout from "../layout/userLayout";
import ReactLoading from 'react-loading';
import axios from "../api/axios";
import * as XLSX from 'xlsx'
import { saveAs } from "file-saver";
import { Select, Pagination } from "antd";
import { toast } from "react-toastify";

const CustomerLabelPage = () => {

    const [load, setLoad] = useState(false);
    const [listLabelActivated, setListLabelActivated] = useState([]);
    const [listCustomer, setListCustomer] = useState([]);

    // set up select label
    const [selectedLabelIds, setSelectedLabelIds] = useState([]);
    // Hàm xử lý sự kiện khi giá trị thay đổi
    const handleChange = (selectedValues, selectedOptions) => {
        // Lấy danh sách label_id từ selectedOptions
        const selectedIds = selectedOptions.map((option) => parseInt(option.key, 10));

        // Cập nhật state với danh sách label_id mới
        setSelectedLabelIds(selectedIds);
    };

    async function getlistLabelActivated() {
        const result = await axios.get(axios.defaults.baseURL + `/api/label/activated`);
        setLoad(true);
        setListLabelActivated(result?.data?.data);
    };

    async function getListCustomerByLabelId() {
        const result = await axios.post(axios.defaults.baseURL + `/api/CustomerLabel/ByLabelId`, selectedLabelIds);
        setLoad(true);
        setListCustomer(result?.data?.data);
    }


    useEffect(() => {
        getlistLabelActivated();
    }, [load]);

    useEffect(() => {
        if (selectedLabelIds.length > 0) {
            getListCustomerByLabelId();
        }
    }, [load, selectedLabelIds]);

    // phân trang
    const [pageSize, setPageSize] = useState(12);
    const [currentPage, setCurrentPage] = useState(1);
    const paginate = (array, pageNumber, pageSize) => {
        const startIndex = (pageNumber - 1) * pageSize;
        const endIndex = startIndex + pageSize;
        return array.slice(startIndex, endIndex);
    };
    const currenCustomerList = paginate(listCustomer, currentPage, pageSize);
    const handlePageChange = (pageNumber, pageSize) => {
        setCurrentPage(pageNumber);
    };

    // xuất file excel tập khách hàng
    const handleExportExcel = () => {
        const workbook = XLSX.utils.book_new();

        if (listCustomer.length === 0) {
            toast.error("Chưa có dữ liệu để xuất file");
        }
        else {
            const modifiedData = listCustomer.map(item => ({
                'ID khách hàng': item.customerResponse.customer_id,
                'Tên khách hàng': item.customerResponse.customer_name,
                'Gender': item.customerResponse.gender,
                'Birthday': item.customerResponse.birthday,
                'Email': item.customerResponse.email,
                'Phone': item.customerResponse.phone,
                'District': item.customerResponse.district,
                'Province': item.customerResponse.province,
                'Label': item.label_name,
            }));

            // Sheet 1: Thu nhập
            const incomeSheet = XLSX.utils.json_to_sheet(modifiedData);
            XLSX.utils.book_append_sheet(workbook, incomeSheet, 'Khách hàng');

            // Xuất file
            const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
            const dataBlob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
            const fileName = 'Danh sách khách hàng';
            saveAs(dataBlob, fileName);
        }
    };

    return (
        <>
            {load ? (
                <div className="d-flex justify-content-center">
                    <div className="table-container" style={{ minWidth: "90%" }}>
                        <div className="justify-content-center">
                            <div className="row">
                                <div className="col text-start">
                                    <Select style={{ width: "100%" }} mode="multiple" placeholder="Chọn label cần xuất tập khách hàng" allowClear onChange={handleChange}>
                                        {listLabelActivated.map((label) => (
                                            <Select.Option key={label?.label_id} value={label?.label_name}>
                                                {label?.label_name}
                                            </Select.Option>
                                        ))}
                                    </Select>
                                </div>
                                <div className="col text-center">
                                    <h4 className="pb-2 mb-0">Danh sách khách hàng</h4>
                                </div>
                                <div className="col text-end">
                                    <button className="btn btn-default low-height-btn bg-success" style={{ height: "100%" }} onClick={handleExportExcel}>
                                        Export Excel
                                    </button>
                                </div>
                            </div>
                            <div className="d-flex text-muted overflow-auto center">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" className="col-1 col-name">Mã khách hàng</th>
                                            <th scope="col" className="col-2 col-name">Tên khách hàng</th>
                                            <th scope="col" className="col-1 col-name">Giới tính</th>
                                            <th scope="col" className="col-1 col-name">Birthday</th>
                                            <th scope="col" className="col-1 col-name">Email</th>
                                            <th scope="col" className="col-1 col-name">Địa chỉ</th>
                                            <th scope="col" className="col-2 col-name">Label</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {listCustomer.map((customer) => (
                                            <tr className="tr-hover" key={customer.customer_id}>
                                                <td>{customer.customerResponse.customer_id}</td>
                                                <td>{customer.customerResponse.customer_name}</td>
                                                <td>{customer.customerResponse.gender}</td>
                                                <td>{customer.customerResponse.birthday}</td>
                                                <td>{customer.customerResponse.email}</td>
                                                <td>{customer.customerResponse.district} - {customer.customerResponse.province}</td>
                                                <td>{customer.label_name}</td>
                                            </tr>))}
                                    </tbody>
                                </table>
                            </div>
                            <div className="row">
                                <div className="col">
                                    <Pagination
                                        onChange={handlePageChange}
                                        current={currentPage}
                                        pageSize={pageSize}
                                        total={listCustomer.length}
                                    />
                                </div>
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
        </>
    );
};

export default userLayout(CustomerLabelPage);