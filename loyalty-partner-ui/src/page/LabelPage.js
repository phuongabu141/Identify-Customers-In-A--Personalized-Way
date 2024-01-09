import React, { useEffect, useState, useCallback } from 'react';
import ReactFlow, { useEdgesState, useNodesState, MiniMap, Controls, Background, addEdge } from 'reactflow';
import userLayout from "../layout/userLayout";
import ReactLoading from "react-loading";
import axios from "../api/axios";
import axiosApiInstance from "../api/interceptor";
import { Button, Form, Modal } from "react-bootstrap";
import { Pagination, Input } from "antd";
import { FaSearch } from "react-icons/fa";
import 'reactflow/dist/style.css';
import { toast } from 'react-toastify';

const LabelPage = () => {

    const [load, setLoad] = useState(false);
    const [listLabel, setListLabel] = useState([]);
    const [listGroupByLabelId, setListGroupByLabelId] = useState([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShowDetailLabel = (e) => {
        setShow(true);
    }

    async function getAllLabel() {
        const result = await axios.get(axios.defaults.baseURL + `/api/label/all`);
        setLoad(true);
        setListLabel(result?.data?.data);
    }

    // phân trang
    const [pageSize, setPageSize] = useState(12);
    const [currentPage, setCurrentPage] = useState(1);
    const paginate = (array, pageNumber, pageSize) => {
        const startIndex = (pageNumber - 1) * pageSize;
        const endIndex = startIndex + pageSize;
        return array.slice(startIndex, endIndex);
    };
    const currentLabelList = paginate(listLabel, currentPage, pageSize);
    const handlePageChange = (pageNumber, pageSize) => {
        setCurrentPage(pageNumber);
    };

    async function getGroupByLabelId(labelId) {
        const result = await axios.get(axios.defaults.baseURL + `/api/group/byLabel/${labelId}`);
        setLoad(true);
        setListGroupByLabelId(result?.data?.data);
        handleShowDetailLabel();
    }

    const handleRowLabelClick = (labelId) => {
        getGroupByLabelId(labelId);
    }

    useEffect(() => {
        getAllLabel();
    }, [load]);

    // xử lý cây label
    const TreeGraph = ({ data }) => {
        const initialNodes = [];
        const initialEdges = [];
        let rootNodePositionY = 0;
        let leftXPosition = 500;
        let rightXPosition = 500;

        const addElement = (group, index) => {
            const levelHeight = 100;

            // Set type to 'input' for the root node
            const nodeType = group.head_group_id === null ? 'input' : 'default';

            if (group.head_group_id === null) {
                rootNodePositionY = index * levelHeight;
            }

            const label = group.head_group_id !== null && group.conditionResponse.attributeResponse.attribute_name !== null && group.conditionResponse.operatorResponse.notation !== null && group.conditionResponse.value !== null
                ? `${group.conditionResponse.attributeResponse.attribute_name} ${group.conditionResponse.operatorResponse.notation} ${group.conditionResponse.value}`
                : `${group.group_id} ${group.logicResponse.logic_notation}`;

            // Tính toán vị trí x của node con dựa trên hướng của chúng
            const xPosition = group.head_group_id === null ? 500 : (group.direction === 'left' ? leftXPosition-- : rightXPosition++);

            initialNodes.push({
                id: group.group_id.toString(),
                type: nodeType,
                data: { label },
                position: { x: xPosition, y: index * levelHeight - rootNodePositionY },
            });

            if (group.head_group_id !== null) {
                initialEdges.push({
                    id: `${group.group_id}-edge`,
                    source: group.head_group_id.toString(),
                    target: group.group_id.toString(),
                    type: 'smoothstep',
                });
            }
        };

        data.forEach(addElement);

        const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
        const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);
        const [hidden, setHidden] = useState(false);

        const onConnect = useCallback((params) => setEdges((els) => addEdge(params, els)), []);
        useEffect(() => {
            setNodes((nds) => nds.map(hide(hidden)));
            setEdges((eds) => eds.map(hide(hidden)));
        }, [hidden]);

        return (
            <ReactFlow
                nodes={nodes}
                edges={edges}
                onNodesChange={onNodesChange}
                onEdgesChange={onEdgesChange}
                onConnect={onConnect}
            >
                <Controls />
            </ReactFlow>
        );
    };

    const hide = (hidden) => (nodeOrEdge) => {
        nodeOrEdge.hidden = hidden;
        return nodeOrEdge;
    };

    // tìm kiếm label
    const [searchText, setSearchText] = useState("");

    const handleKeyPress = (e) => {
        if (e.key == "Enter") {
            setSearchText(e.target.value);
        }
    };

    async function listLabelSearch() {
        const encodedSearchText = encodeURIComponent(searchText);
        const result = await axios.get(axios.defaults.baseURL + `/api/label/search?labelName=${encodedSearchText}`
        );
        if(result?.data?.data.length === 0)
        {
            toast.info("Không có label cần tìm kiếm");
        }
        else
        {
            setListLabel(result?.data?.data);
        }
        
    };

    useEffect(() => {
        listLabelSearch();
    }, [searchText]);

    return (
        <>
            {load ? (
                <div className="d-flex justify-content-center">
                    <div className="table-container" style={{ minWidth: "90%" }}>
                        <div className="justify-content-center">
                            <div className="row">
                                <div className="col text-start">
                                    <h4 className="pb-2 mb-0">Danh sách Label</h4>
                                </div>
                                <div className="col text-end">
                                    <Input
                                        style={{ width: "200px" }}
                                        size="small"
                                        placeholder="Tìm kiếm label"
                                        prefix={<FaSearch />}
                                        onKeyDown={handleKeyPress}
                                    />
                                </div>
                            </div>
                            <div className="d-flex text-muted overflow-auto center">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" className="col-1 col-name">Mã Label</th>
                                            <th scope="col" className="col-4 col-name">Tên label</th>
                                            <th scope="col" className="col-1 col-name">Trạng thái label</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {currentLabelList.map((item) => (
                                            <tr className="tr-hover" key={item.label_id} onClick={() => handleRowLabelClick(item.label_id)}>
                                                <td>{item.label_id}</td>
                                                <td>{item.label_name}</td>
                                                <td>{item.status}</td>
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
                                        total={listLabel.length}
                                    />
                                </div>
                                <div className="col" style={{ textAlign: "right" }}>
                                    <h6>Bấm vào một label để xem chi tiết</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
                :
                (
                    <div className={"center loading"}>
                        <ReactLoading
                            type={"cylon"}
                            color="#fffff"
                            height={"11px"}
                            width={"9%"}
                        />
                    </div>
                )

            }
            <Modal show={show} onHide={handleClose} className="custom-modal">
                <Modal.Header closeButton>
                    <Modal.Title>Thông tin chi tiết label {listGroupByLabelId[0]?.labelResponse?.label_name}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <div style={{ width: '100%', height: '500px' }}>
                            <TreeGraph data={listGroupByLabelId} />
                        </div>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Đóng
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
};

export default userLayout(LabelPage);

