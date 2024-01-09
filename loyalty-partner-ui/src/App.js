import './App.css';
import { Route, Routes } from 'react-router-dom';
import PartnerRequest from './page/PartnerRequest';
import CustomerLabelPage from './page/CustomerLabelPage';
import NotificationPage from './page/NotificationPage';
import LabelPage from './page/LabelPage';
import LoginPage from './page/LoginPage';

function App() {
  return (
    <Routes>
      <Route path='/' element={<LoginPage/>}></Route>
      <Route path='/login' element={<LoginPage/>}></Route>
      <Route path='/label' element={<LabelPage/>}></Route>
      <Route path='/partner-request' element={<PartnerRequest/>}></Route>
      <Route path='/customer-label' element={<CustomerLabelPage/>}></Route>
      <Route path='/notification' element={<NotificationPage/>}></Route>
    </Routes>
  );
};

export default App;
