
import './App.css';

// material-ui 컴포넌트 
import { Paper, List, Container } from '@mui/material';


// 컴포넌트 
import { useState } from 'react';
import Todo from './Components/Todo';
import AddTodo from './Components/AddTodo';


function App() {
  // 출력할 내용 설정
  const [items, setItems] = useState([]);


  

  const Add = (item) =>{
    item.id = "ID-"+items.length;
    item.done = false;
    // 기존 배열 복사후 새 값 추가
    setItems([...items,item]);
  }
  
  const DeleteItem = (item) =>{
    // 삭제할 아이템을 찾는다
    const newItems = items.filter(e => e.id !==item.id);
    // 삭제할 아이템을 제외한 아이템을 다시 배열에 저장
    setItems([...newItems]);
  }
  const editItem = () =>{
    setItems([...items]);
  }


  // items 길이만큼 출력
  let todoItems = items.length > 0 && (
    <Paper style={{ margin: '16' }} >
      <List>
        {
          //items 출력
          items.map((item) => {
            return (
              <Todo item={item} key={item.id} editItem={editItem} deleteItem={DeleteItem}/>
            )
          })
        }
      </List>
    </Paper>
  )

return (
  <div className="App" >
    <Container maxWidth="md">
      <AddTodo Add={Add}></AddTodo>
      <div className="TodoList">{todoItems}</div>
    </Container>
  </div>
);

}


export default App;
