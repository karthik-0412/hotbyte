import React, { useState } from 'react';
 
function AdmissionForm() {

  // Individual state for each field

  const [name, setName] = useState('');

  const [email, setEmail] = useState('');

  const [password, setPassword] = useState('');

  const [course, setCourse] = useState('');

  const [about, setAbout] = useState('');
 
  const [errors, setErrors] = useState({});

  const [submittedList, setSubmittedList] = useState([]);
 
  const courses = ['Select Course', 'B.Tech', 'MBA', 'B.Sc', 'B.Com'];
 
  const validate = () => {

    const newErrors = {};

    if (!name) newErrors.name = 'Name is required';

    if (!email.includes('@')) newErrors.email = 'Valid email required';

    if (password.length < 6) newErrors.password = 'Min 6 chars';

    if (!course || course === 'Select Course') newErrors.course = 'Select a course';

    if (!about) newErrors.about = 'Write something';

    return newErrors;

  };
 
  const handleSubmit = (e) => {

    e.preventDefault();

    const validationErrors = validate();

    if (Object.keys(validationErrors).length === 0) {

      const newEntry = { name, email, password, course, about };

      setSubmittedList([...submittedList, newEntry]);
 
      // Clear form fields

      setName('');

      setEmail('');

      setPassword('');

      setCourse('');

      setAbout('');

      setErrors({});

    } else {

      setErrors(validationErrors);

    }

  };
 
  return (
<div style={{ padding: 20, maxWidth: 500, margin: 'auto' }}>
<h2>Admission Form</h2>
<form onSubmit={handleSubmit}>
<div>
<label>Name:</label><br />
<input value={name} onChange={(e) => setName(e.target.value)} />
<div style={{ color: 'red' }}>{errors.name}</div>
</div>
 
        <div>
<label>Email:</label><br />
<input value={email} onChange={(e) => setEmail(e.target.value)} />
<div style={{ color: 'red' }}>{errors.email}</div>
</div>
 
        <div>
<label>Password:</label><br />
<input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
<div style={{ color: 'red' }}>{errors.password}</div>
</div>
 
        <div>
<label>Course:</label><br />
<select value={course} onChange={(e) => setCourse(e.target.value)}>

            {courses.map((c, i) => (
<option key={i} value={c}>{c}</option>

            ))}
</select>
<div style={{ color: 'red' }}>{errors.course}</div>
</div>
 
        <div>
<label>About:</label><br />
<textarea value={about} onChange={(e) => setAbout(e.target.value)} />
<div style={{ color: 'red' }}>{errors.about}</div>
</div>
 
        <button type="submit">Submit</button>
</form>
 
      {submittedList.length > 0 && (
<div>
<h3>Submitted Data</h3>
<ul>

            {submittedList.map((item, idx) => (
<li key={idx}>

                {item.name} - {item.email} - {item.course}
</li>

            ))}
</ul>
</div>

      )}
</div>

  );

}
 
export default AdmissionForm;

 