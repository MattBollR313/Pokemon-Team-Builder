import './App.css';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import Footer from './components/footer/Footer';

function App() {

  return (
    <div className="App">
      <div className="content-wrap">
        <Routes>
          <Route path="/" element={<Layout/>}>
            <Route path="/" element={<Home/>}></Route>
          </Route>
        </Routes>
      </div>
      <Footer/>
    </div>
  );
  
}

export default App;
