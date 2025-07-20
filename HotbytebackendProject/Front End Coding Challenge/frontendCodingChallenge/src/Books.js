import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { Paginator } from 'primereact/paginator';
import { Toast } from 'primereact/toast';
import 'primereact/resources/themes/lara-light-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default function Books() {
  const [books, setBooks] = useState([]);
  const [globalFilter, setGlobalFilter] = useState('');
  const [form, setForm] = useState({
    isbn: '',
    title: '',
    author: '',
    publicationYear: ''
  });
  const [editMode, setEditMode] = useState(false);
  const [visible, setVisible] = useState(false);
  const [first, setFirst] = useState(0);
  const [rows, setRows] = useState(5);
  const [isbnSearch, setIsbnSearch] = useState('');

  const toast = useRef(null);
  const navigate = useNavigate();

  useEffect(() => { fetchBooks(); }, []);

  const fetchBooks = async () => {
    try {
      const token = localStorage.getItem('token');
      const res = await axios.get('http://localhost:8085/api/book', {
        headers: { Authorization: `Bearer ${token}` }
      });
      setBooks(res.data);
    } catch (err) {
      console.error('Fetch books error:', err);
      navigate('/');
    }
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Frontend ISBN validation before sending to backend
    const isbnPattern = /^([0-9]{10}|[0-9]{13})$/;
    if (!isbnPattern.test(form.isbn)) {
      toast.current.show({
        severity: 'error',
        summary: 'Invalid ISBN',
        detail: 'ISBN must be either 10 or 13 digits long',
        life: 3000
      });
      return;
    }

    const token = localStorage.getItem('token');
    try {
      if (editMode) {
        await axios.put(`http://localhost:8085/api/book/${form.isbn}`, {
          ...form,
          publicationYear: parseInt(form.publicationYear)
        }, {
          headers: { Authorization: `Bearer ${token}` }
        });
        toast.current.show({
          severity: 'success',
          summary: 'Updated',
          detail: 'Book updated successfully',
          life: 3000
        });
      } else {
        await axios.post('http://localhost:8085/api/book', {
          ...form,
          publicationYear: parseInt(form.publicationYear)
        }, {
          headers: { Authorization: `Bearer ${token}` }
        });
        toast.current.show({
          severity: 'success',
          summary: 'Added',
          detail: 'Book added successfully',
          life: 3000
        });
      }
      fetchBooks();
      setForm({ isbn: '', title: '', author: '', publicationYear: '' });
      setEditMode(false);
      setVisible(false);
    } catch (err) {
      console.error('Submit book error:', err);
      toast.current.show({
        severity: 'error',
        summary: 'Error',
        detail: 'Failed to submit book',
        life: 3000
      });
    }
  };

  const handleEdit = (book) => {
    setForm(book);
    setEditMode(true);
    setVisible(true);
  };

  const handleDelete = async (isbn) => {
    const token = localStorage.getItem('token');
    try {
      await axios.delete(`http://localhost:8085/api/book/${isbn}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchBooks();
      toast.current.show({
        severity: 'success',
        summary: 'Deleted',
        detail: 'Book deleted successfully',
        life: 3000
      });
    } catch (err) {
      console.error('Delete book error:', err);
      toast.current.show({
        severity: 'error',
        summary: 'Error',
        detail: 'Failed to delete book',
        life: 3000
      });
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  const actionBodyTemplate = (rowData) => (
    <>
      <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => handleEdit(rowData)} style={{ marginRight: '0.75vw' }} />
      <Button icon="pi pi-trash" className="p-button-rounded p-button-danger" onClick={() => handleDelete(rowData.isbn)} />
    </>
  );

  const handleSearchByIsbn = () => {
    const filtered = books.filter(b =>
      b.isbn.toLowerCase().includes(isbnSearch.toLowerCase())
    );
    setGlobalFilter(''); // clear general filter
    setFirst(0); // reset pagination
    setBooks(filtered.length > 0 ? filtered : []);
  };

  const onPageChange = (event) => {
    setFirst(event.first);
    setRows(event.rows);
  };

  const filteredBooks = books.filter(b =>
    b.title.toLowerCase().includes(globalFilter.toLowerCase()) ||
    b.author.toLowerCase().includes(globalFilter.toLowerCase()) ||
    b.isbn.toLowerCase().includes(globalFilter.toLowerCase())
  );

  const paginatedBooks = filteredBooks.slice(first, first + rows);

  return (
    <div className="p-4" style={{ maxWidth: '900px', margin: '0 auto' }}>
      <Toast ref={toast} />
      <div className="p-d-flex p-jc-between p-ai-center mb-4">
        <h2>Books</h2>
        <Button label="Logout" icon="pi pi-sign-out" className="p-button-danger" onClick={handleLogout} style={{ marginLeft: '50vw', marginTop: '-10vh', marginBottom: '5vh' }} />
      </div>

      <div className="p-inputgroup mb-4" style={{ marginBottom: '5vh' }}>
        <InputText
          placeholder="Search books..."
          value={globalFilter}
          onChange={(e) => setGlobalFilter(e.target.value)}
          style={{ marginRight: '10px', borderRadius: '10px' }}
        />
        <InputText
          placeholder="Search by ISBN"
          value={isbnSearch}
          onChange={(e) => setIsbnSearch(e.target.value)}
          style={{ marginRight: '10px', borderRadius: '10px' }}
        />
        <Button
          label="Search by ISBN"
          icon="pi pi-search"
          className="p-button-info"
          onClick={handleSearchByIsbn}
          style={{ marginRight: '10px', borderRadius: '10px' }}
          outlined
        />
        <Button
          label="Add Book"
          icon="pi pi-plus"
          className="p-button-success"
          onClick={() => {
            setEditMode(false);
            setForm({ isbn: '', title: '', author: '', publicationYear: '' });
            setVisible(true);
          }}
          style={{ marginRight: '10px', borderRadius: '10px' }}
        />
        <Button
          label="Reset"
          icon="pi pi-refresh"
          className="p-button-secondary"
          onClick={() => { fetchBooks(); setIsbnSearch(''); }}
          style={{ borderRadius: '10px' }}
          outlined
        />
      </div>

      <DataTable value={paginatedBooks} responsiveLayout="scroll">
        <Column field="isbn" header="ISBN" sortable></Column>
        <Column field="title" header="Title" sortable></Column>
        <Column field="author" header="Author" sortable></Column>
        <Column field="publicationYear" header="Year" sortable></Column>
        <Column body={actionBodyTemplate} header="Actions"></Column>
      </DataTable>

      <Paginator first={first} rows={rows} totalRecords={filteredBooks.length} rowsPerPageOptions={[5, 10, 20]} onPageChange={onPageChange} />

      <Dialog header={editMode ? "Edit Book" : "Add Book"} visible={visible} style={{ width: '400px' }} onHide={() => setVisible(false)}>
        <form onSubmit={handleSubmit} className="p-fluid">
          <div className="p-field">
            <label htmlFor="isbn">ISBN</label>
            <InputText
              id="isbn"
              name="isbn"
              value={form.isbn}
              onChange={handleChange}
              required
              disabled={editMode}
              pattern="^[0-9]{10}$|^[0-9]{13}$"
              title="ISBN must be either 10 or 13 digits"
              style={{ marginBottom: '2vh' }}
            />
          </div>
          <div className="p-field">
            <label htmlFor="title">Title</label>
            <InputText
              id="title"
              name="title"
              value={form.title}
              onChange={handleChange}
              required
              style={{ marginBottom: '2vh' }}
            />
          </div>
          <div className="p-field">
            <label htmlFor="author">Author</label>
            <InputText
              id="author"
              name="author"
              value={form.author}
              onChange={handleChange}
              required
              style={{ marginBottom: '2vh' }}
            />
          </div>
          <div className="p-field">
            <label htmlFor="publicationYear">Publication Year</label>
            <InputText
              id="publicationYear"
              name="publicationYear"
              type="number"
              value={form.publicationYear}
              onChange={handleChange}
              required
              style={{ marginBottom: '2vh' }}
            />
          </div>
          <Button type="submit" label={editMode ? "Update" : "Add"} className="p-button-success" />
        </form>
      </Dialog>
    </div>
  );
}
